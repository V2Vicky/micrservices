package com.ting.order.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ting.order.dto.OrderDTO;
import com.ting.order.entity.Order;
import com.ting.order.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ModelMapper mapper;

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

	public OrderDTO createOrder(OrderDTO orderDTO) {
		Order order = convertToEntity(orderDTO);
		order.setTimestamp(LocalDateTime.now());
		order = orderRepository.save(order);
		return convertToDTO(order);
	}

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
