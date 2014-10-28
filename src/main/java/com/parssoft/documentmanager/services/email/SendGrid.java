package com.parssoft.documentmanager.services.email;

import com.parssoft.documentmanager.utils.EmailPostUtils;
import static com.parssoft.documentmanager.utils.EmailPostUtils.SENDGRID_USERNAME;
import static com.parssoft.documentmanager.utils.EmailPostUtils.SENDGRID_PASSWORD;
import com.parssoft.documentmanager.utils.GenericUtilities;
import com.sendgrid.SendGrid.Email;
import com.sendgrid.SendGridException;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class uses the SendGrid email implementation for sending emails
 *
 * Created on Sep 13, 2014, 2:21:47 PM
 *
 * @author Ade
 *
 * Copyright(c) 2014 Copyright(c) 2014 ParsSoft. All Rights Reserved. This software is the proprietary information of
 * ParsSoft.
 *
 */
public class SendGrid {

	private static final Logger log = LogManager.getLogger(SendGrid.class.getName());

	/**
	 * Sends confirmation email
	 *
	 * @param fromAddress
	 * @param toAddress
	 */
	public static void sendConfirmationEmailUsingSendGrid(String fromAddress, String toAddress) {
		com.sendgrid.SendGrid sendGrid
				= new com.sendgrid.SendGrid(SENDGRID_USERNAME, SENDGRID_PASSWORD);

		Email email = new Email();
		if (EmailPostUtils.TO_2 != null) {
			email.addCc(EmailPostUtils.TO_2);
		}

		fromAddress = GenericUtilities.emailAddressOnly(fromAddress);
		email.addTo(toAddress);
		email.setFrom(fromAddress);
		email.setSubject(EmailPostUtils.CONFIRMATION_EMAIL_SUBJECT);
//		email.setText(EmailPostUtils.CONFIRMATION_EMAIL_BODY);
		try {
			email.setHtml(EmailPostUtils.getConfirmationHtml());

			com.sendgrid.SendGrid.Response response = sendGrid.send(email);
			log.info("Email sent!  Response was: " + response.getMessage());
		} catch (SendGridException | IOException ex) {
			GenericUtilities.logException(log, ex);
			log.error("The email was not sent.", ex);
		}
	}
}
