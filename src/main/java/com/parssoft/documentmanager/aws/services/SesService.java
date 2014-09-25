package com.parssoft.documentmanager.aws.services;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.parssoft.documentmanager.aws.utils.AwsCredentialsHandler;
import com.parssoft.documentmanager.aws.utils.RegionConfiguration;
import com.parssoft.documentmanager.utils.EmailPostUtils;
import com.parssoft.documentmanager.utils.GenericUtilities;
import java.nio.ByteBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Created on Jul 22, 2014, 4:02:44 PM
 *
 * @author Ade
 *
 * Copyright(c) 2014 ParsSoft. All Rights Reserved. This software is the proprietary information of ParsSoft.
 *
 */
public class SesService {

	private static final Logger log = LoggerFactory.getLogger(SesService.class.getName());

	public SesService() {
	}

	/**
	 * Send email using Amazon SES
	 * @param emailBody
	 * @param attachment
	 */
	public void sendEmail(String emailBody, ByteBuffer attachment) {

		Destination destination = new Destination().withToAddresses(
				new String[]{EmailPostUtils.TO_1});
		// this doesnt work
		// .withCcAddresses(new String[] {EmailPostUtils.TO_2})
//				new String[]{EmailPostUtils.TO_1, EmailPostUtils.TO_2});

		Content subject = new Content().withData(EmailPostUtils.SUBJECT);
		Content textBody = new Content().withData(emailBody);

		Body body = new Body().withText(textBody);

		Message message = new Message().withSubject(subject).withBody(body);

		SendEmailRequest request
				= new SendEmailRequest().withSource(EmailPostUtils.FROM)
				.withDestination(destination).withMessage(message);

		try {
			log.info("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");

			AmazonSimpleEmailServiceClient client
					= new AmazonSimpleEmailServiceClient(AwsCredentialsHandler.getCredentials());
			client.setRegion(RegionConfiguration.getRegion());

			// Send the email.
			client.sendEmail(request);
			log.info("Email sent!");

		} catch (IllegalArgumentException ex) {
			GenericUtilities.logException(log, ex);
			log.error("The email was not sent.", ex);
		}
	}

	/**
	 * Uses Amazon SES to send confirmation emails
	 *
	 * @param fromAddress
	 * @Deprecated
	 */
	public static void sendConfirmationEmailUsingSES(String fromAddress) {

		Destination destination = new Destination()
				.withToAddresses(new String[] {fromAddress});

		Content subject = new Content().withData(EmailPostUtils.CONFIRMATION_EMAIL_SUBJECT);
		Content textBody = new Content().withData(EmailPostUtils.CONFIRMATION_EMAIL_BODY);

		Body body = new Body().withText(textBody);

		Message message = new Message().withSubject(subject).withBody(body);

		SendEmailRequest request = new SendEmailRequest().withSource(fromAddress)
				.withDestination(destination).withMessage(message);

		try {
			log.info("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");

			AmazonSimpleEmailServiceClient client
					= new AmazonSimpleEmailServiceClient(AwsCredentialsHandler.getCredentials());
			client.setRegion(RegionConfiguration.getRegion());

			// Send the email.
			client.sendEmail(request);
			log.info("Email sent!");
		} catch (IllegalArgumentException ex) {
			GenericUtilities.logException(log, ex);
			log.error("The email was not sent.", ex);
		}
	}

}
