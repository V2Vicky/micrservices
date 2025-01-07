package com.ting.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ting.order.dto.OrderDTO;
import com.ting.order.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping
	public List<OrderDTO> getAllOrders() {
		return orderService.getAllOrders();
	}

	@GetMapping("/{id}")
	public OrderDTO getOrderById(@PathVariable Long id) {
		return orderService.getOrderById(id);
	}

//	@GetMapping("/user/{userId}")
//	public List<OrderDTO> getOrdersByUserId(@PathVariable Long userId) {
//		return orderService.getOrdersByUserId(userId);
//	}
//
//	@GetMapping("/restaurant/{restaurantId}")
//	public List<OrderDTO> getOrdersByRestaurantId(@PathVariable Long restaurantId) {
//		return orderService.getOrdersByRestaurantId(restaurantId);
//	}

	@PostMapping
	public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
		return orderService.createOrder(orderDTO);
	}

	@PatchMapping("/{id}/status")
	public OrderDTO updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
		return orderService.updateOrderStatus(id, status);
	}

	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
	}
}
