package com.ting.notification.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ting.notification.dto.NotificationDTO;
import com.ting.notification.entity.Notification;
import com.ting.notification.repository.NotificationRepository;

@Service
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private ModelMapper mapper;

	public List<NotificationDTO> getAllNotifications() {
		return notificationRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public NotificationDTO getNotificationById(Long id) {
		Notification notification = notificationRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Notification not found"));
		return convertToDTO(notification);
	}

//	public List<NotificationDTO> getNotificationsByUserId(Long userId) {
//		return notificationRepository.findByUserId(userId).stream().map(this::convertToDTO)
//				.collect(Collectors.toList());
//	}

	public NotificationDTO createNotification(NotificationDTO notificationDTO) {
		Notification notification = convertToEntity(notificationDTO);
		notification.setTimestamp(LocalDateTime.now());
		notification.setRead(false);
		return convertToDTO(notificationRepository.save(notification));
	}

	public NotificationDTO markAsRead(Long id) {
		Notification notification = notificationRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Notification not found"));
		notification.setRead(true);
		return convertToDTO(notificationRepository.save(notification));
	}

	public void deleteNotification(Long id) {
		notificationRepository.deleteById(id);
	}

	private NotificationDTO convertToDTO(Notification notification) {
		return mapper.map(notification, NotificationDTO.class);
	}

	private Notification convertToEntity(NotificationDTO notificationDTO) {
		return mapper.map(notificationDTO, Notification.class);
	}
}
