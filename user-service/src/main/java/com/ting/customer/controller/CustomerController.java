package com.ting.customer.controller;


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

import com.ting.customer.dto.CustomerDTO;
import com.ting.customer.service.CustomerService;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public List<CustomerDTO> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/{id}")
	public CustomerDTO getCustomerById(@PathVariable Long id) {
		return customerService.getCustomerById(id);
	}

	@PostMapping
	public CustomerDTO createCustomer(@RequestBody CustomerDTO CustomerDTO) {
		return customerService.createCustomer(CustomerDTO);
	}

	@PutMapping("/{id}")
	public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO CustomerDTO) {
		return customerService.updateCustomer(id, CustomerDTO);
	}

	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
	}
}
