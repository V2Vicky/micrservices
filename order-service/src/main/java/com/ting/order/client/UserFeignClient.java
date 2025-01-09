package com.ting.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ting.order.dto.UserDTO;

@FeignClient(name = "user-service")
public interface UserFeignClient {

	@GetMapping("/{id}")
	UserDTO getUserById(@PathVariable Long id);
}
