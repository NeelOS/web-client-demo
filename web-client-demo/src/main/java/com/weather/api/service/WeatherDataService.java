package com.weather.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weather.api.client.WeatherApiWebClient;
import com.weather.api.model.ApiRequest;
import com.weather.api.model.ResponseObjectData;
import com.weather.api.model.WeatherApiRes;
import com.weather.api.model.WeatherDataRes;
import com.weather.api.modeler.WeatherApiResponseModeler;

@Service
public class WeatherDataService {

	@Autowired
	private WeatherApiResponseModeler resposeModeler;

	@Autowired
	private WeatherApiWebClient weatherApiWebClient;

	public ResponseObjectData getWeatherData(final ApiRequest apiRequest) {
		final ResponseObjectData responseObjectData = new ResponseObjectData();
		final WeatherApiRes weatherApiRes = weatherApiWebClient.getWeatherData(apiRequest);
		final WeatherDataRes weatherDataRes = resposeModeler.processWeatherResponse(weatherApiRes);
		responseObjectData.setData(weatherDataRes);
		return responseObjectData;
	}

}
