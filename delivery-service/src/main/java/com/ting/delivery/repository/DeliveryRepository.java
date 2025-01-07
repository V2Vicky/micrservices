package com.ting.delivery.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ting.delivery.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

//	List<Delivery> findByOrderId(Long orderId);
//
//	List<Delivery> findByDeliveryPersonId(Long deliveryPersonId);
}
