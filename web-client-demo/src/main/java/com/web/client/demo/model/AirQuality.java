package com.web.client.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AirQuality {

	 private Double co;
	 private Double no2;
	 private Double o3;
	 private Double so2;
	 private Double pm2_5;
	 private Double pm10;
	 @JsonProperty("us-epa-index")
	 private int us_epa_index;
	 @JsonProperty("gb-defra-index")
	 private int gb_defra_index;
}
