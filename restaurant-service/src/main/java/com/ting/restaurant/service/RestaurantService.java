package com.ting.restaurant.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ting.restaurant.dto.MenuItemDTO;
import com.ting.restaurant.dto.RestaurantDTO;
import com.ting.restaurant.entity.MenuItem;
import com.ting.restaurant.entity.Restaurant;
import com.ting.restaurant.repository.RestaurantRepository;

@Service
public class RestaurantService {
	
	@Autowired
    private  RestaurantRepository restaurantRepository;

	@Autowired
	private ModelMapper mapper;

    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public RestaurantDTO getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found"));
        return convertToDTO(restaurant);
    }


	public RestaurantDTO getRestaurantByName(String name) {
		Restaurant restaurant = restaurantRepository.findByName(name)
				.orElseThrow(() -> new RuntimeException("Restaurant not found"));
		return convertToDTO(restaurant);
	}

	public void updateMenu(Long restaurantId, List<MenuItemDTO> updatedMenuDTOs) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        List<MenuItem> updatedMenu = updatedMenuDTOs.stream()
				.map(dto -> mapper.map(dto, MenuItem.class))
                .collect(Collectors.toList());

		restaurant.setMenuItems(updatedMenu);
        restaurantRepository.save(restaurant);
	}
    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = convertToEntity(restaurantDTO);
        restaurant = restaurantRepository.save(restaurant);
        return convertToDTO(restaurant);
    }

    public RestaurantDTO updateRestaurant(Long id, RestaurantDTO restaurantDTO) {
        Restaurant existing = restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found"));
        existing.setName(restaurantDTO.getName());
        existing.setLocation(restaurantDTO.getLocation());
        existing.setRating(restaurantDTO.getRating());
        return convertToDTO(restaurantRepository.save(existing));
    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    private RestaurantDTO convertToDTO(Restaurant restaurant) {
		return mapper.map(restaurant, RestaurantDTO.class);
    }

    private Restaurant convertToEntity(RestaurantDTO dto) {
		return mapper.map(dto, Restaurant.class);
    }
}

