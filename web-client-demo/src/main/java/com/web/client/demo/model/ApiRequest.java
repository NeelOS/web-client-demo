package com.web.client.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiRequest {

	@NotEmpty(message = "Empty City Field")
	private String city;
	@NotEmpty(message = "Empty boolean field for AQI")
	private boolean aqiRequired;
}
