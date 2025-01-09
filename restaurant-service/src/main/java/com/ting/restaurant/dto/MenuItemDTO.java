package com.ting.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDTO {
	private Long id;
	private String itemName;
	private double price;
	private int quantity;
	private Boolean availability;
}
