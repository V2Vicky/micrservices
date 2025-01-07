package com.ting.notification.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
	private Long id;
	private Long userId;
	private String type; // Notification type
	private String message; // Content of the notification
	private Boolean read; // Status of notification
	private LocalDateTime timestamp; // Timestamp of notification
}
