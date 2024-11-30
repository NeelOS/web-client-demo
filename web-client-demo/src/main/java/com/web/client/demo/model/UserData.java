package com.web.client.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class UserData {

	private List<String> businessPhones;
	private String displayName;
	private String givenName;
	private String jobTitle;
	private String mail;
	private String mobilePhone;
	private String officeLocation;
	private String preferredLanguage;
	private String surname;
	private String userPrincipalName;
	private String id;
}
