package com.ting.order.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	private Long userId;
//	private Long restaurantId;
	private Double totalAmount;
	private String status;
	private LocalDateTime timestamp;

//	@ElementCollection
//	private List<OrderItem> items;
}

//@Embeddable
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//class OrderItem {
//	private Long menuItemId;
//	private Integer quantity;
//}
