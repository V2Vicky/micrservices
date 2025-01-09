package com.ting.order.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	private Long id;
	private Long userId;
	private Long restaurantId;
	private Double totalAmount;
	private String status;
	private LocalDateTime timestamp;
	private List<OrderItemDTO> items;

}
