package com.web.client.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.client.demo.client.WeatherApiWebClient;
import com.web.client.demo.model.ApiRequest;
import com.web.client.demo.model.ResponseObjectData;
import com.web.client.demo.model.WeatherApiRes;
import com.web.client.demo.model.WeatherDataRes;
import com.web.client.demo.modeler.WeatherApiResponseModeler;

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
