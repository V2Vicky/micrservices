package com.ting.customer.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

	@Bean
	public ModelMapper mapper() {

		return new ModelMapper();
	}
}
