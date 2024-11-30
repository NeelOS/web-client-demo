package com.web.client.demo.modeler;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.web.client.demo.model.UserProfileApiRes;
import com.web.client.demo.model.UserProfileDataRes;

@Component
public class UserProfileDataModeler {

	public UserProfileDataRes processUpsData(final UserProfileApiRes upsApiResponse) {
		final UserProfileDataRes upsData = new UserProfileDataRes();
		
		if(CollectionUtils.isNotEmpty(upsApiResponse.getValue())) {
			
			final String mobileNum  = StringUtils.join(upsApiResponse.getValue().get(0).getBusinessPhones(), ',');
			upsData.setUserId(StringUtils.defaultIfEmpty(upsApiResponse.getValue().get(0).getId(), "NA"));
			upsData.setFirstName(StringUtils.defaultIfEmpty(upsApiResponse.getValue().get(0).getGivenName(), "NA"));
			upsData.setLastName(StringUtils.defaultIfEmpty(upsApiResponse.getValue().get(0).getSurname(), "NA"));
			upsData.setFullName(StringUtils.defaultIfEmpty(upsApiResponse.getValue().get(0).getDisplayName(), "NA"));
			upsData.setEmailId(StringUtils.defaultIfEmpty(upsApiResponse.getValue().get(0).getMail(), "NA"));
			upsData.setMobileNum(StringUtils.defaultIfEmpty(mobileNum, "NA"));
			upsData.setDesignation(StringUtils.defaultIfEmpty(upsApiResponse.getValue().get(0).getJobTitle(), "NA"));
			upsData.setOfficeLocation(StringUtils.defaultIfEmpty(upsApiResponse.getValue().get(0).getOfficeLocation(), "NA"));
			upsData.setLanguageSpoken(StringUtils.defaultIfEmpty(upsApiResponse.getValue().get(0).getPreferredLanguage(), "NA"));
		}
		return upsData;
		
		
	}
	

}
