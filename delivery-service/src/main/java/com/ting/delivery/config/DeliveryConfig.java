package com.ting.delivery.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeliveryConfig {

	@Bean
	public ModelMapper mapper() {

		return new ModelMapper();
	}

}
