package com.weather.api.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.weather.api.model.ApiRequest;
import com.weather.api.model.WeatherApiRes;

@Component
public class WeatherApiWebClient {

	@Value("${weather.api.key}")
	private String apiKey;

	@Autowired
	private WebClient webClient;

	public WeatherApiRes getWeatherData(final ApiRequest apiRequest) {
		final String city = apiRequest.getCity();
		final String aqi = apiRequest.isAqiRequired() ? "yes" : "no";
		final String url = "https://api.weatherapi.com/v1/current.json?key={key}&q={city}&aqi={aqi}";
		final Map<String, String> params = new HashMap<>();
		params.put("key", apiKey);
		params.put("city", city);
		params.put("aqi", aqi);
		return webClient.get().uri(url, params).accept(MediaType.APPLICATION_JSON).retrieve()
				.bodyToMono(WeatherApiRes.class).block();
	}

}
