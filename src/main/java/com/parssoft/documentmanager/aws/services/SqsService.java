package com.parssoft.documentmanager.aws.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.parssoft.documentmanager.aws.utils.AwsCredentialsHandler;
import com.parssoft.documentmanager.aws.utils.RegionConfiguration;
import com.parssoft.documentmanager.utils.EmailPostUtils;
import com.parssoft.documentmanager.utils.GenericUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is our SQS messaging service. It is used to send messages to the Amazon SQS service
 *
 * Created on Jul 18, 2014, 8:52:41 PM
 *
 * @author Ade
 *
 * Copyright(c) 2014 ParsSoft. All Rights Reserved. This software is the proprietary information of ParsSoft.
 *
 */
public class SqsService {

	private static final AmazonSQS sqs =
			new AmazonSQSClient(AwsCredentialsHandler.getCredentials());

	private static final Logger log = LoggerFactory.getLogger(SqsService.class);

	public SqsService() {
		sqs.setRegion(RegionConfiguration.getRegion());
	}

	/**
	 * Add the email data to the inbound queue
	 *
	 * @param jsonDocument
	 */
	public void addMailDataToInboundQueue(String jsonDocument) {

		try {
			sqs.sendMessage(new SendMessageRequest(EmailPostUtils.SQS_INBOUND_EMAIL_QUEUE_NAME, jsonDocument));
		} catch (AmazonServiceException ase) {
			GenericUtilities.logAmazonServiceException(log, ase);
		}
	}

}
