package com.web.client.demo.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.client.demo.model.ResponseObjectData;
import com.web.client.demo.service.UserProfileService;
import com.web.client.demo.util.CommonUtil;

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
