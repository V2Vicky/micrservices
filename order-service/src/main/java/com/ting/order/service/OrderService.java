package com.ting.order.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ting.order.client.RestaurantFeignClient;
import com.ting.order.client.UserFeignClient;
import com.ting.order.dto.MenuDTO;
import com.ting.order.dto.OrderDTO;
import com.ting.order.dto.OrderItemDTO;
import com.ting.order.dto.RestaurantDTO;
import com.ting.order.dto.UserDTO;
import com.ting.order.entity.Order;
import com.ting.order.repository.OrderRepository;

@Service
public class OrderService {

	private OrderRepository orderRepository;
	private UserFeignClient userFeignClient;
	private RestaurantFeignClient restaurantFeignClient;

	@Transactional
	public OrderDTO createOrder(OrderDTO orderDTO) {

		UserDTO user = userFeignClient.getUserById(orderDTO.getUserId());

		RestaurantDTO restaurant = restaurantFeignClient.getRestaurantById(orderDTO.getRestaurantId());

		BigDecimal totalCost = BigDecimal.ZERO;

		for (OrderItemDTO item : orderDTO.getItems()) {
			MenuDTO menuItem = restaurant.getMenuItems().stream()
					.filter(menu -> menu.getId().equals(item.getMenuItemId())).findFirst()
					.orElseThrow(() -> new RuntimeException("Menu item not found: " + item.getMenuItemId()));

			if (menuItem.getQuantity() < item.getQuantity()) {
				throw new RuntimeException("Insufficient quantity for menu item: " + menuItem.getId());
			}

			menuItem.setQuantity(menuItem.getQuantity() - item.getQuantity());

			// Calculate cost for the current item and add to total cost
			BigDecimal itemCost = menuItem.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
			totalCost = totalCost.add(itemCost);
		}

		// Save updated menu to Restaurant Service
		restaurantFeignClient.updateMenu(restaurant.getId(), restaurant.getMenuItems());

		// Create and save Order entity
		Order order = new Order();
		order.setId(UUID.randomUUID());
		order.setUserId(orderDTO.getUserId());
		order.setRestaurantId(orderDTO.getRestaurantId());
		order.setOrderTime(LocalDateTime.now());
		order.setMenuItemIds(menuItems.stream().map(menu -> menu.getId()).collect(Collectors.toList()));
		order.setTotalCost(totalCost);
		order.setStatus("CREATED");

		Order savedOrder = orderRepository.save(order);

		// Convert saved Order to DTO and return
		return convertToDTO(savedOrder);
	}


	public List<OrderDTO> getAllOrders() {
		return orderRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public OrderDTO getOrderById(Long id) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
		return convertToDTO(order);
	}

//	public List<OrderDTO> getOrdersByUserId(Long userId) {
//		return orderRepository.findByUserId(userId).stream().map(this::convertToDTO).collect(Collectors.toList());
//	}
//
//	public List<OrderDTO> getOrdersByRestaurantId(Long restaurantId) {
//		return orderRepository.findByRestaurantId(restaurantId).stream().map(this::convertToDTO)
//				.collect(Collectors.toList());
//	}


	public OrderDTO updateOrderStatus(Long id, String status) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
		order.setStatus(status);
		return convertToDTO(orderRepository.save(order));
	}

	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);
	}

	private OrderDTO convertToDTO(Order order) {
		return mapper.map(order, OrderDTO.class);
	}

	private Order convertToEntity(OrderDTO orderDTO) {
		return mapper.map(orderDTO, Order.class);
	}
}
