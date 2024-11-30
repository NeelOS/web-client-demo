package com.web.client.demo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.web.client.demo.model.UserProfileApiRes;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UpsWebClient {

	
	@Autowired
	private WebClient azureUpsWebClient;
	

	private static final String URI = "https://graph.microsoft.com/v1.0/users";

	public UserProfileApiRes getUserProfile() {
		final UserProfileApiRes defaultUpsApiResponse = new UserProfileApiRes();
		return azureUpsWebClient.get()
				.uri(URI)
				.attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId("ups"))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(HttpStatusCode::isError, clientResponse -> {
					log.error("Error in calling Weather API, Status code : {} ", clientResponse.statusCode());
					return clientResponse.bodyToMono(String.class).map(Exception::new);
				})
				.bodyToMono(UserProfileApiRes.class)
				.defaultIfEmpty(defaultUpsApiResponse)
				.block();
	}

}
