package com.ting.delivery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long orderId; // References the associated order ID
	private Long deliveryPersonId; // References the ID of the delivery person
	private String status; // e.g., ASSIGNED, IN_TRANSIT, DELIVERED
	private String estimatedArrivalTime; // Estimated delivery time (as a string or timestamp)
}
