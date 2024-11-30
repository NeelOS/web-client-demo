package com.web.client.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class UserProfileDataRes {

	private String userId;
	private String fullName;
	private String firstName;
	private String lastName;
	private String designation;
	private String emailId;
	private String mobileNum;
	private String officeLocation;
	private String languageSpoken;
	
}
