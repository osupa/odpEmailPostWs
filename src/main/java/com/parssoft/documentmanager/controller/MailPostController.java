package com.parssoft.documentmanager.controller;


import com.parssoft.documentmanager.model.Email;
import com.parssoft.documentmanager.model.EmailLight;
import com.parssoft.documentmanager.rest.services.QueueServicesClient;
import com.parssoft.documentmanager.services.email.EmailProcessor;
import com.parssoft.documentmanager.services.email.SendGrid;


import static com.parssoft.documentmanager.utils.GenericUtilities.getCurrentTimeAsyyyyMMddDashHHmmss;
import static com.parssoft.documentmanager.utils.GenericUtilities.logException;
import java.util.Date;
import org.slf4j.Logger;
import java.io.IOException;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
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
	
	private static final Logger logger = LoggerFactory.getLogger(MailPostController.class);

	/**
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

	/*
		boolean isAttachment = false;
		File localFile = null;
		File encryptedFile = null;

		// process attachment
		if ((mail.getAttachments() != null) && (mail.getAttachments().length > 0)) {
			isAttachment = true;
			logger.debug("There is an attachment in this email.");

			localFile = new File(EmailPostUtils.LOCAL_FILE_STORE
					+ mail.getAttachment1().getOriginalFilename());
			try {
				mail.getAttachment1().transferTo(localFile);

				// encrypt the file
				encryptedFile = FileEncryptionUtils.encrypt(localFile);
				mail.setFileName(encryptedFile.getName());
				mail.setFileSize(encryptedFile.length());

				logger.debug("Attachment saved locally as: " + localFile.getName());
			} catch (IOException | IllegalStateException | FileEncryptionException ex) {
				logException(logger, ex);

	*/

	@RequestMapping(value = "/rest/email/test",
					headers = "content-type=multipart/form-data",
					consumes = {"multipart/form-data", "text/plain", "application/*"},
					method = RequestMethod.POST)
	public @ResponseBody String test (@ModelAttribute Email mail) throws IOException {
			logger.debug("Sending to DynamoDB queue manager");

			getTestMail(mail);

			QueueServicesClient client = new QueueServicesClient();
			client.postToEmailInboundQueue(mail);
			
		return "welcome";
	}

	private void getTestMail(Email mail) {
		mail.setFrom("osupa@adepanko.com");
			mail.setTo("osupa@adepanko.com");
			mail.setSubject("Test subject " + getCurrentTimeAsyyyyMMddDashHHmmss());

			mail.setText("Hard coded test!!!");
			mail.setBody(mail.getText());
			mail.setReceivedTime(new Date());

			mail.setAttachment1(null);

			SendGrid.sendConfirmationEmailUsingSendGrid(mail.getFrom());
	}
}
