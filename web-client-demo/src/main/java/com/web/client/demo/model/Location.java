package com.web.client.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Location {
	
	private String name;
	private String region;
	private String country;
	private Double lat;
	private Double lon;
	private String tz_id;
    private String localtime;

}
