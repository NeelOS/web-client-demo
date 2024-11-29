package com.weather.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Current {

	private String last_updated;
	private Double temp_c;
	private Double temp_f;
	private int is_day;
	private Double wind_mph;
	private Double wind_kph;
	private int wind_degree;
	private String wind_dir;
	private Double pressure_mb;
	private Double pressure_in;
	private Double precip_mm;
	private Double precip_in;
	private int humidity;
	private int cloud;
	private Double feelslike_c;
	private Double feelslike_f;
	private Double windchill_c;
	private Double windchill_f;
	private Double heatindex_c;
	private Double heatindex_f;
	private Double dewpoint_c;
	private Double dewpoint_f;
	private Double vis_miles;
	private Double uv;
	private Double gust_mph;
	private Double gust_kph;
	private AirQuality air_quality;
	private Condition condition;

}
