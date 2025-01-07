package com.ting.order.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ting.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
//	List<Order> findByUserId(Long userId);
//
//	List<Order> findByRestaurantId(Long restaurantId);
}
