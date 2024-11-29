package com.web.client.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class WeatherDataRes {

	private String city;
	private String state;
	private String country;
	private Double latitude;
	private Double longitude;
	private String datetime;
	private String lastUpdateDateTime;
	private Double temperature;
	private String dayOrNight;
	private String weatherCondition;
	private Double windSpeed;
	private String windDirection;
	private AQIDataRes AQIParameters;
}
