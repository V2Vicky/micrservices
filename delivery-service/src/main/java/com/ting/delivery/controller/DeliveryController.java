package com.ting.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ting.delivery.dto.DeliveryDTO;
import com.ting.delivery.service.DeliveryService;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

	@Autowired
	private DeliveryService deliveryService;

	@GetMapping
	public List<DeliveryDTO> getAllDeliveries() {
		return deliveryService.getAllDeliveries();
	}

	@GetMapping("/{id}")
	public DeliveryDTO getDeliveryById(@PathVariable Long id) {
		return deliveryService.getDeliveryById(id);
	}

//	@GetMapping("/order/{orderId}")
//	public List<DeliveryDTO> getDeliveriesByOrderId(@PathVariable Long orderId) {
//		return deliveryService.getDeliveriesByOrderId(orderId);
//	}
//
//	@GetMapping("/person/{deliveryPersonId}")
//	public List<DeliveryDTO> getDeliveriesByDeliveryPersonId(@PathVariable Long deliveryPersonId) {
//		return deliveryService.getDeliveriesByDeliveryPersonId(deliveryPersonId);
//	}

	@PostMapping
	public DeliveryDTO createDelivery(@RequestBody DeliveryDTO deliveryDTO) {
		return deliveryService.createDelivery(deliveryDTO);
	}

	@PutMapping("/{id}")
	public DeliveryDTO updateDelivery(@PathVariable Long id, @RequestBody DeliveryDTO deliveryDTO) {
		return deliveryService.updateDelivery(id, deliveryDTO);
	}

	@DeleteMapping("/{id}")
	public void deleteDelivery(@PathVariable Long id) {
		deliveryService.deleteDelivery(id);
	}
}
