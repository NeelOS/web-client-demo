package com.web.client.demo.config;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

public class AzureUpsOAuth2ClientRepository implements ClientRegistrationRepository {

	private String clientId;
	private String clientSecret;
	private String tokenUri;
	private String scope;

	public AzureUpsOAuth2ClientRepository(final String clientId, final String clientSecret, final String tokenUri,
			final String scope) {

		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.tokenUri = tokenUri;
		this.scope = scope;
	}

	@Override
	public ClientRegistration findByRegistrationId(final String registrationId) {
		return ClientRegistration.withRegistrationId(registrationId).tokenUri(tokenUri).clientId(clientId)
				.clientSecret(clientSecret).scope(scope)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS).build();
	}

}
