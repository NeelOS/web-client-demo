package com.web.client.demo.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.web.client.demo.model.ApiRequest;
import com.web.client.demo.model.WeatherApiRes;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WeatherApiWebClient {

	@Value("${weather.api.key}")
	private String apiKey;
	
	private static final String URI = "https://api.weatherapi.com/v1/current.json?key={key}&q={city}&aqi={aqi}";

	@Autowired
	private WebClient webClient;

	public WeatherApiRes getWeatherData(final ApiRequest apiRequest) {
		final WeatherApiRes defaultResponse = new WeatherApiRes();
		final String city = apiRequest.getCity();
		final String aqi = apiRequest.isAqiRequired() ? "yes" : "no";
		final Map<String, String> params = new HashMap<>();
		params.put("key", apiKey);
		params.put("city", city);
		params.put("aqi", aqi);
		return webClient.get()
				.uri(URI, params)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(HttpStatusCode::isError, clientResponse -> {
					log.error("Error in calling Weather API, Status code : {} ", clientResponse.statusCode());
					return clientResponse.bodyToMono(String.class).map(Exception::new) ;
				})
				.bodyToMono(WeatherApiRes.class)
				.defaultIfEmpty(defaultResponse)
				.block();
	}

}
