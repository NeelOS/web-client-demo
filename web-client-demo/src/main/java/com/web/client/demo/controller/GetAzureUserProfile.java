package com.web.client.demo.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.client.demo.model.ResponseObjectData;
import com.web.client.demo.service.UserProfileService;
import com.web.client.demo.util.CommonUtil;

/**
 * This Controller gets basic user profile for the user whose client id and client secret is setup in the Azure portal and
 * added in application.properties file. This example show how you can write web client which internally takes care of calling the 
 * Oauth token api and manages the token in memory unti it exipires. More details of azure token setup is provided the word doc in this
 * project.
 */
@RestController
public class GetAzureUserProfile {
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private UserProfileService userProfileService;
	
	@PostMapping("/getUserProfile")
	public ResponseEntity<ResponseObjectData> getUserProfile(){
		
		final Instant start = Instant.now();
		try {
			return commonUtil.getSuccessResponse(userProfileService.getUserProfile(), start, "getUserProfile");
		} catch (final Exception exception) {
			return commonUtil.getFailureResponse(exception, start, "getUserProfile");
		}
		
	}

}
