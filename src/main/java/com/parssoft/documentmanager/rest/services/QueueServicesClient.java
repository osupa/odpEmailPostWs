package com.parssoft.documentmanager.rest.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.parssoft.documentmanager.model.Email;
import com.parssoft.documentmanager.model.QueueServiceAuth;
import com.parssoft.documentmanager.utils.EmailPostUtils;
import com.parssoft.documentmanager.utils.JSonUtility;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * Created on Sep 1, 2014, 10:47:47 PM
 *
 * @author Ade Panko <ade@adepanko.com>
 *
 * Copyright(c) 2014  Copyright(c) 2014 ParsSoft. All Rights Reserved.
 * This software is the proprietary information of ParsSoft.
 *
 */
public class QueueServicesClient {

	public QueueServicesClient() {}

	/**
	 *
	 * @param email
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public void postToEmailInboundQueue(Email email)
			throws JsonProcessingException, IOException {
		QueueServiceAuth accessToken = getAccessToken();

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		RestTemplate template = new RestTemplate();

		HttpEntity<String> requestEntity
				= new HttpEntity<>(JSonUtility.convertBeanToJsonString(email), headers);

		String response = template.postForObject(
				EmailPostUtils.QUEUE_SERVICE_URL_EMAIL.concat(
						accessToken.getAccessToken()),
					requestEntity, String.class);

System.out.println("response = " + response);
	}


	/**
	 * Retrieve our access authorization token
	 *
	 * @return
	 * @throws java.io.IOException
	 */
	public QueueServiceAuth getAccessToken() throws IOException {
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		RestTemplate template = new RestTemplate();

		HttpEntity<String> requestEntity
				= new HttpEntity<>(EmailPostUtils.QUEUE_MANAGER_CREDENTIALS, headers);

		ResponseEntity<String> entity = template.postForEntity(
				EmailPostUtils.QUEUE_SERVICE_URL_AUTH,
				requestEntity, String.class);

		QueueServiceAuth auth
				= (QueueServiceAuth) JSonUtility.convertJsonToBean(
						QueueServiceAuth.class, entity.getBody());
		
		return auth;
	}
}
