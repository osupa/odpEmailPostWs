package com.parssoft.documentmanager.controller;

import com.parssoft.documentmanager.model.Email;
import com.parssoft.documentmanager.model.EmailLight;
import com.parssoft.documentmanager.rest.services.QueueServicesClient;
import com.parssoft.documentmanager.services.email.EmailProcessor;


import static com.parssoft.documentmanager.utils.GenericUtilities.getCurrentTimeAsyyyyMMddDashHHmmss;
import static com.parssoft.documentmanager.utils.GenericUtilities.logException;
import java.util.Date;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * This class is our main SendGrid entry point.  It provides a restful web service
 * end point for incoming email messages with attachments
 *
 * @author Ade
 *
 * Copyright(c) 2014 ParsSoft. All Rights Reserved.
 * This software is the proprietary information of ParsSoft.
 *
 */
@Controller
@SessionAttributes
public class MailPostController {
	
	private static final Logger logger = LogManager.getLogger(MailPostController.class);

	/**
	 * This is our restful SendGrid endpoint
	 *
	 * @param mail
	 * @return
	 */
	@RequestMapping(value = "/rest/email/emailPost",
					headers = "content-type=multipart/form-data",
					consumes = {"multipart/form-data", "text/plain", "application/*"},
					method = RequestMethod.POST)
	public @ResponseBody EmailLight postEmailMultiPart(@ModelAttribute Email mail) {

		logger.debug("Rest service call came in at " + getCurrentTimeAsyyyyMMddDashHHmmss());
		
		EmailProcessor eProcessor = new EmailProcessor();
		try {
			mail = eProcessor.process(mail);
		} catch (IOException | IllegalStateException ex) {
				logException(logger, ex);
		}
		return mail;
	}

	/**
	 * Test endpoint - to use, make public and test
	 * @param mail
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/rest/email/test",
					headers = "content-type=multipart/form-data",
					consumes = {"multipart/form-data", "text/plain", "application/*"},
					method = RequestMethod.POST)
	public @ResponseBody String test (@ModelAttribute Email mail) throws IOException {
			logger.debug("Sending to DynamoDB queue manager");

			try {
				getTestMail(mail);

				QueueServicesClient client = new QueueServicesClient();
				client.postToEmailInboundQueue(mail);
			} catch (IOException | IllegalStateException ex) {
				logException(logger, ex);
			}
//		return "welcome";
		return null;
	}

	/**
	 * Test2 endpoint - to use, make public and test
	 * @param mail
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/rest/email/test2",
					headers = "content-type=multipart/form-data",
					consumes = {"multipart/form-data", "text/plain", "application/*"},
					method = RequestMethod.POST)
	public @ResponseBody String test (String one) throws IOException {
			logger.debug("In test2 method.");
String two = "zz two";
			logger.debug(one + " - " + two);
			Email mail = new Email();
//			try {
				getTestMail(mail);

				QueueServicesClient client = new QueueServicesClient();
//				client.postToEmailInboundQueue(mail);
//			} catch (IOException | IllegalStateException ex) {
//				logException(logger, ex);
//			}
////		return "welcome";
		return "zzTake";
	}

	private void getTestMail(Email mail) {
		mail.setFrom("osupa@adepanko.com");
			mail.setTo("osupa@adepanko.com");
			mail.setSubject("Test subject " + getCurrentTimeAsyyyyMMddDashHHmmss());

			mail.setText("Hard coded test!!!");
			mail.setBody(mail.getText());
			mail.setReceivedTime(new Date());

			mail.setAttachment1(null);

//			SendGrid.sendConfirmationEmailUsingSendGrid(mail.getFrom());
	}
}
