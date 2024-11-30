package com.web.client.demo.service;

import org.springframework.stereotype.Service;

import com.web.client.demo.model.ResponseObjectData;

@Service
public class UserProfileService {

	public ResponseObjectData getUserProfile() {
		final ResponseObjectData responseObjectData = new ResponseObjectData();
		final String upsMsg = "Development in progress";
		responseObjectData.setData(upsMsg);
		return responseObjectData;
	}

}
