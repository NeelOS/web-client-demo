package com.web.client.demo.modeler;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.client.demo.model.AQIDataRes;
import com.web.client.demo.model.WeatherApiRes;
import com.web.client.demo.model.WeatherDataRes;
import com.web.client.demo.util.CommonUtil;

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
		
		if(ObjectUtils.isNotEmpty(weatherApiRes.getCurrent().getAir_quality())) {
			final AQIDataRes aqiDataRes = new AQIDataRes();
			aqiDataRes.setCarbon_mono_oxide(weatherApiRes.getCurrent().getAir_quality().getCo());
			aqiDataRes.setNitrogen_di_oxide(weatherApiRes.getCurrent().getAir_quality().getNo2());
			aqiDataRes.setOzone(weatherApiRes.getCurrent().getAir_quality().getO3());
			aqiDataRes.setSulpher_di_oxide(weatherApiRes.getCurrent().getAir_quality().getSo2());
			aqiDataRes.setPm_two_point_five(weatherApiRes.getCurrent().getAir_quality().getPm2_5());
			aqiDataRes.setPm_ten(weatherApiRes.getCurrent().getAir_quality().getPm10());
			weatherDataRes.setAQIParameters(aqiDataRes);
		}
		return weatherDataRes;
	}

}
