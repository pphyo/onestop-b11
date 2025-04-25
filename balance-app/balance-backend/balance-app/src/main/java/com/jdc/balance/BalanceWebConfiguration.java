package com.jdc.balance;

import java.time.format.DateTimeFormatter;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Configuration
public class BalanceWebConfiguration implements WebMvcConfigurer {
	
	@Bean
	Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
		return builder -> {
			builder.simpleDateFormat("yyyy-MM-dd");
			builder.serializers(new LocalDateSerializer(dateFormat()));
			builder.serializers(new LocalDateTimeSerializer(dateTimeFormat()));
			builder.deserializers(new LocalDateDeserializer(dateFormat()));
			builder.deserializers(new LocalDateTimeDeserializer(dateTimeFormat()));
		};
	}
	
	@Bean
	DateTimeFormatter dateFormat() {
		return DateTimeFormatter.ISO_LOCAL_DATE;
	}
	
	@Bean
	DateTimeFormatter dateTimeFormat() {
		return DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedHeaders("*")
				.allowedMethods("POST", "PUT", "GET", "DELETE", "PATCH")
				.allowedOrigins("http://localhost:5173")
				.exposedHeaders("*");
	}

}
