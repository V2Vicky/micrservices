package com.ting.notification.entity;

import java.time.LocalDateTime;

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
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userId; // User receiving the notification
	private String type; // Notification type (e.g., ORDER_UPDATE, DELIVERY_UPDATE)
	private String message; // Notification content
	private Boolean read; // True if the notification is read, otherwise false
	private LocalDateTime timestamp; // When the notification was sent
}
