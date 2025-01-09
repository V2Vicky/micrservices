package com.ting.restaurant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ting.restaurant.entity.MenuItem;
import com.ting.restaurant.entity.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

	Optional<Restaurant> findByName(String name);

	void updateMenu(Long restaurantId, List<MenuItem> menu);
}