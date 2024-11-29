package com.web.client.demo.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.client.demo.model.ApiRequest;
import com.web.client.demo.model.ResponseObjectData;
import com.web.client.demo.service.WeatherDataService;
import com.web.client.demo.util.CommonUtil;

/**
 * This controller get the weather data from weather api using a simple web client
 * Authorization used is api key which is static
 * API key is hard coded in application.properties file
 * Login to https://www.weatherapi.com/my/ to get the new api key in case of existing one gets expired after some day
 * use id iiest.indraneel@gmail.com and password you all ready know
 * Endpoint : http://localhost:8084/web-client-demo/getWeatherData
 */
@RestController
public class GetWeatherDataController {
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private WeatherDataService weatherDataService;

	
	
	@PostMapping("/getWeatherData")
	public ResponseEntity<ResponseObjectData> getWeatherData(@RequestBody final ApiRequest apiRequest){
		
		final Instant start = Instant.now();
		try {
			return commonUtil.getSuccessResponse(weatherDataService.getWeatherData(apiRequest), start, "getWeatherData");
		} catch (final Exception exception) {
			return commonUtil.getFailureResponse(exception, start, "getWeatherData");
		}
		
	}
}
