package com.weather.api.modeler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weather.api.model.WeatherApiRes;
import com.weather.api.model.WeatherDataRes;
import com.weather.api.util.CommonUtil;

@Component
public class WeatherApiResponseModeler {
	
	@Autowired
	private CommonUtil commonUtil;

	public WeatherDataRes processWeatherResponse(final WeatherApiRes weatherApiRes) {
		final WeatherDataRes weatherDataRes = new WeatherDataRes();
		weatherDataRes.setCity(weatherApiRes.getLocation().getName());
		weatherDataRes.setCountry(weatherApiRes.getLocation().getCountry());
		weatherDataRes.setState(weatherApiRes.getLocation().getRegion());
		weatherDataRes.setLatitude(weatherApiRes.getLocation().getLat());
		weatherDataRes.setLongitude(weatherApiRes.getLocation().getLon());
		weatherDataRes.setDatetime(weatherApiRes.getLocation().getLocaltime());
		weatherDataRes.setDayOrNight(commonUtil.isDayOrNight(weatherApiRes.getCurrent().getIs_day()));
		weatherDataRes.setLastUpdateDateTime(weatherApiRes.getCurrent().getLast_updated());
		weatherDataRes.setTemperature(weatherApiRes.getCurrent().getTemp_c());
		weatherDataRes.setWeatherCondition(weatherApiRes.getCurrent().getCondition().getText());
		weatherDataRes.setWindDirection(weatherApiRes.getCurrent().getWind_dir());
		weatherDataRes.setWindSpeed(weatherApiRes.getCurrent().getWind_kph());
		return weatherDataRes;
	}

}
