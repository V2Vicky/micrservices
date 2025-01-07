package com.ting.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {
	private Long id;
	private String itemName;
	private Double price;
	private Boolean availability;
}
