package com.weather.api.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.weather.api.model.ApiRequest;
import com.weather.api.model.ResponseObjectData;
import com.weather.api.service.WeatherDataService;
import com.weather.api.util.CommonUtil;

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
