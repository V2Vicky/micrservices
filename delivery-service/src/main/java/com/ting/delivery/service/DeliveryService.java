package com.ting.delivery.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ting.delivery.dto.DeliveryDTO;
import com.ting.delivery.entity.Delivery;
import com.ting.delivery.repository.DeliveryRepository;

@Service
public class DeliveryService {

	@Autowired
	private DeliveryRepository deliveryRepository;

	@Autowired
	private ModelMapper mapper;

	public List<DeliveryDTO> getAllDeliveries() {
		return deliveryRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public DeliveryDTO getDeliveryById(Long id) {
		Delivery delivery = deliveryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Delivery not found"));
		return convertToDTO(delivery);
	}

//	public List<DeliveryDTO> getDeliveriesByOrderId(Long orderId) {
//		return deliveryRepository.findByOrderId(orderId).stream().map(this::convertToDTO).collect(Collectors.toList());
//	}
//
//	public List<DeliveryDTO> getDeliveriesByDeliveryPersonId(Long deliveryPersonId) {
//		return deliveryRepository.findByDeliveryPersonId(deliveryPersonId).stream().map(this::convertToDTO)
//				.collect(Collectors.toList());
//	}

	public DeliveryDTO createDelivery(DeliveryDTO deliveryDTO) {
		Delivery delivery = convertToEntity(deliveryDTO);
		return convertToDTO(deliveryRepository.save(delivery));
	}

	public DeliveryDTO updateDelivery(Long id, DeliveryDTO deliveryDTO) {
		Delivery existing = deliveryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Delivery not found"));
		existing.setOrderId(deliveryDTO.getOrderId());
		existing.setDeliveryPersonId(deliveryDTO.getDeliveryPersonId());
		existing.setStatus(deliveryDTO.getStatus());
		existing.setEstimatedArrivalTime(deliveryDTO.getEstimatedArrivalTime());
		return convertToDTO(deliveryRepository.save(existing));
	}

	public void deleteDelivery(Long id) {
		deliveryRepository.deleteById(id);
	}

	private DeliveryDTO convertToDTO(Delivery delivery) {
		return mapper.map(delivery, DeliveryDTO.class);
	}

	private Delivery convertToEntity(DeliveryDTO deliveryDTO) {
		return mapper.map(deliveryDTO, Delivery.class);
	}
}
