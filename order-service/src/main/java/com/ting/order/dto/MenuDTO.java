package com.ting.order.dto;

import java.math.BigDecimal;

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
	private String name;
	private BigDecimal price;
	private Integer quantity;
	private Boolean availability;
}
