package com.web.client.demo.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.StreamReadConstraints;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;


/**
 * This web client configuration is using client manager class
 */
@Configuration
public class UpsWebClientConfig {

	@Value("${azure-ad.token.uri}")
	private String tokenUri;

	@Value("${azure-ad.token.client-id}")
	private String clientId;

	@Value("${azure-ad.token.client-secret}")
	private String clientSecret;

	@Value("${azure-ad.token.scope}")
	private String scope;

	@Bean
	public AuthorizedClientServiceOAuth2AuthorizedClientManager getClientManager() {
		final ClientRegistrationRepository upsClientRepo = new AzureUpsOAuth2ClientRepository(clientId, clientSecret,
				tokenUri, scope);
		final OAuth2AuthorizedClientService authorizedClientService = new InMemoryOAuth2AuthorizedClientService(
				upsClientRepo);
		return new AuthorizedClientServiceOAuth2AuthorizedClientManager(upsClientRepo, authorizedClientService);
	}

	@Bean("azureUpsWebClient")
	public WebClient upsWebClient(final AuthorizedClientServiceOAuth2AuthorizedClientManager clientManager) {
		final ObjectMapper objMapper = new ObjectMapper();
		objMapper.getFactory()
				.setStreamReadConstraints(StreamReadConstraints.builder().maxStringLength(10000000).build());
		final ServletOAuth2AuthorizedClientExchangeFilterFunction oAuth2 = new ServletOAuth2AuthorizedClientExchangeFilterFunction(
				clientManager);
		oAuth2.setDefaultClientRegistrationId("ups");
		final ConnectionProvider provider = ConnectionProvider.builder("web-client-demo").maxConnections(500)
				.maxIdleTime(Duration.ofSeconds(20)).maxLifeTime(Duration.ofSeconds(60))
				.pendingAcquireTimeout(Duration.ofSeconds(60)).evictInBackground(Duration.ofSeconds(120)).build();
		final HttpClient httpClient = HttpClient.create(provider).compress(true)
				.responseTimeout(Duration.ofSeconds(60));
		final int size = 16 * 1024 * 1024;
		final ExchangeStrategies exchangeStratagies = ExchangeStrategies.builder().codecs(configurer -> {
			configurer.defaultCodecs().maxInMemorySize(size);
			configurer.defaultCodecs()
					.jackson2JsonEncoder(new Jackson2JsonEncoder(objMapper, MediaType.APPLICATION_JSON));
			configurer.defaultCodecs()
					.jackson2JsonDecoder(new Jackson2JsonDecoder(objMapper, MediaType.APPLICATION_JSON));
		}).build();

		return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient))
				.exchangeStrategies(exchangeStratagies).filter(oAuth2).build();
	}

}
