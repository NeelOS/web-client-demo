package com.web.client.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.client.demo.client.UpsWebClient;
import com.web.client.demo.model.ResponseObjectData;
import com.web.client.demo.model.UserProfileApiRes;
import com.web.client.demo.model.UserProfileDataRes;
import com.web.client.demo.modeler.UserProfileDataModeler;

@Service
public class UserProfileService {
	
	@Autowired
	private UpsWebClient upsWebClient;
	
	@Autowired
	private UserProfileDataModeler upsDataModeler;

	public ResponseObjectData getUserProfile() {
		final ResponseObjectData responseObjectData = new ResponseObjectData();
		final UserProfileApiRes upsApiResponse = upsWebClient.getUserProfile();
		final UserProfileDataRes upsData = upsDataModeler.processUpsData(upsApiResponse);
		responseObjectData.setData(upsData);
		return responseObjectData;
	}
	

}
