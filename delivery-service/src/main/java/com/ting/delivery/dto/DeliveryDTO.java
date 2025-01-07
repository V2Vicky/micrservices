package com.ting.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDTO {
	private Long id;
	private Long orderId;
	private Long deliveryPersonId;
	private String status; // ASSIGNED, IN_TRANSIT, DELIVERED
	private String estimatedArrivalTime;
}
