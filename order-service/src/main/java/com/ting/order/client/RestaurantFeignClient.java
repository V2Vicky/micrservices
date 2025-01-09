package com.ting.order.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ting.order.dto.MenuDTO;
import com.ting.order.dto.RestaurantDTO;


@FeignClient(name = "restaurant-service")
public interface RestaurantFeignClient {
	@GetMapping("/restaurants/find")
	RestaurantDTO getRestaurantById(@RequestParam("id") Long id);

	@PutMapping("/restaurants/{restaurantId}/menu")
	void updateMenu(@RequestParam("restaurantId") Long restaurantId, @RequestBody List<MenuDTO> menu);
}