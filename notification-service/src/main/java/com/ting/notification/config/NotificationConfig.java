package com.ting.notification.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {

	@Bean
	public ModelMapper mapper() {

		return new ModelMapper();
	}

}
