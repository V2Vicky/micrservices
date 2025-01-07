package com.ting.customer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ting.customer.dto.CustomerDTO;
import com.ting.customer.entity.Customer;
import com.ting.customer.repository.CustomerRepository;


@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper mapper;

	public List<CustomerDTO> getAllCustomers() {
		return customerRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public CustomerDTO getCustomerById(Long id) {
		Customer Customer = customerRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Customer not found"));
		return convertToDTO(Customer);
	}

	public CustomerDTO createCustomer(CustomerDTO CustomerDTO) {
		Customer Customer = convertToEntity(CustomerDTO);
		return convertToDTO(customerRepository.save(Customer));
	}

	public CustomerDTO updateCustomer(Long id, CustomerDTO CustomerDTO) {
		Customer existing = customerRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Customer not found"));
		existing.setName(CustomerDTO.getName());
		existing.setEmail(CustomerDTO.getEmail());
		existing.setAddress(CustomerDTO.getAddress());
		existing.setPhoneNumber(CustomerDTO.getPhoneNumber());
		return convertToDTO(customerRepository.save(existing));
	}

	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}

	private CustomerDTO convertToDTO(Customer Customer) {
		return mapper.map(Customer, CustomerDTO.class);
	}

	private Customer convertToEntity(CustomerDTO CustomerDTO) {
		return mapper.map(CustomerDTO, Customer.class);
	}
}
