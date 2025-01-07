package com.ting.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ting.restaurant.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}