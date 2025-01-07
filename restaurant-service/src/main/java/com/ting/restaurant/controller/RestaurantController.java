package com.ting.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ting.restaurant.dto.RestaurantDTO;
import com.ting.restaurant.service.RestaurantService;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@GetMapping
	public List<RestaurantDTO> getAllRestaurants() {
		return restaurantService.getAllRestaurants();
	}

	@GetMapping("/{id}")
	public RestaurantDTO getRestaurantById(@PathVariable Long id) {
		return restaurantService.getRestaurantById(id);
	}

	@PostMapping
	public RestaurantDTO createRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
		return restaurantService.createRestaurant(restaurantDTO);
	}

	@PutMapping("/{id}")
	public RestaurantDTO updateRestaurant(@PathVariable Long id, @RequestBody RestaurantDTO restaurantDTO) {
		return restaurantService.updateRestaurant(id, restaurantDTO);
	}

	@DeleteMapping("/{id}")
	public void deleteRestaurant(@PathVariable Long id) {
		restaurantService.deleteRestaurant(id);
	}
}
