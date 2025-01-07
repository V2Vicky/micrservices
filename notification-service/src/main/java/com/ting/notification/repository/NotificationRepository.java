package com.ting.notification.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ting.notification.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
//	List<Notification> findByUserId(Long userId);
}
