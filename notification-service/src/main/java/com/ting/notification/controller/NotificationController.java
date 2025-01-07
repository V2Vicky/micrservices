package com.ting.notification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ting.notification.dto.NotificationDTO;
import com.ting.notification.service.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@GetMapping
	public List<NotificationDTO> getAllNotifications() {
		return notificationService.getAllNotifications();
	}

	@GetMapping("/{id}")
	public NotificationDTO getNotificationById(@PathVariable Long id) {
		return notificationService.getNotificationById(id);
	}

//	@GetMapping("/user/{userId}")
//	public List<NotificationDTO> getNotificationsByUserId(@PathVariable Long userId) {
//		return notificationService.getNotificationsByUserId(userId);
//	}

	@PostMapping
	public NotificationDTO createNotification(@RequestBody NotificationDTO notificationDTO) {
		return notificationService.createNotification(notificationDTO);
	}

	@PatchMapping("/{id}/read")
	public NotificationDTO markAsRead(@PathVariable Long id) {
		return notificationService.markAsRead(id);
	}

	@DeleteMapping("/{id}")
	public void deleteNotification(@PathVariable Long id) {
		notificationService.deleteNotification(id);
	}
}
