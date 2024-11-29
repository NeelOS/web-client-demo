package com.web.client.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class AQIDataRes {

	private Double carbon_mono_oxide;
    private Double nitrogen_di_oxide;
    private Double ozone;
    private Double sulpher_di_oxide;
    private Double pm_two_point_five;
    private Double pm_ten;
}
