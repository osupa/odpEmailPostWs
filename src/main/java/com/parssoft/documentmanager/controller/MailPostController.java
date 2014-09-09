package com.parssoft.documentmanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.parssoft.documentmanager.aws.services.S3Service;
import com.parssoft.documentmanager.aws.services.SesService;
import com.parssoft.documentmanager.aws.services.SqsService;
import com.parssoft.documentmanager.model.Email;
import com.parssoft.documentmanager.model.EmailLight;
import com.parssoft.documentmanager.rest.services.QueueServicesClient;
import com.parssoft.documentmanager.utils.EmailPostUtils;
import static com.parssoft.documentmanager.utils.GenericUtilities.getCurrentTimeAsyyyyMMddDashHHmmss;
import static com.parssoft.documentmanager.utils.GenericUtilities.logException;
import com.parssoft.documentmanager.utils.JSonUtility;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.slf4j.Logger;
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

		final String SLASH = "/";
		final String URL_PART_1 = "https://";
		final String URL_PART_2 = "s3-us-west-2.amazonaws.com";

		logger.debug("Rest service call came in at " + getCurrentTimeAsyyyyMMddDashHHmmss());
		// make the SendGrid text field our body
		if (mail.getText() != null) {
			mail.setBody(mail.getText());
		}
		mail.setReceivedTime(new Date());

		File localFile = null;
		boolean isAttachment = false;

		// process attachment
		if ((mail.getAttachments() != null) && (mail.getAttachments().length > 0)) {
			logger.debug("There is an attachment in this email.");
			isAttachment = true;

			mail.setFileName(mail.getAttachment1().getFileItem().getName());
			mail.setFileSize(mail.getAttachment1().getSize());
			localFile = new File(EmailPostUtils.LOCAL_FILE_STORE
					+ mail.getAttachment1().getOriginalFilename());
			try {
				mail.getAttachment1().transferTo(localFile);
				logger.debug("Attachment saved locally as: " + localFile.getName());
			} catch (IOException | IllegalStateException ex) {
				logException(logger, ex);
			}
			mail.setS3OriginalFileLink(URL_PART_1
					+ URL_PART_2
					+ SLASH
					+ EmailPostUtils.S3_EMAIL_DOCUMENT_UPLOAD_DIR
					+ (SLASH + mail.getFileName()));
		}

		// copy file to S3 data store
		SqsService sqsService = new SqsService();
		try {
			logger.debug("Sending to sqs queue at " + getCurrentTimeAsyyyyMMddDashHHmmss());
			sqsService.addMailDataToInboundQueue(JSonUtility.convertBeanToJsonString(mail));

			logger.debug("Sending to DynamoDB based queue manager");
			QueueServicesClient queueServicesClient = new QueueServicesClient();
			queueServicesClient.postToEmailInboundQueue(mail);

			if (isAttachment) {
				S3Service s3Service = new S3Service();
				logger.debug("Sending to S3!!!");
				s3Service.addDocumentToS3Bucket(
					EmailPostUtils.S3_EMAIL_DOCUMENT_UPLOAD_DIR, localFile);
			}
			logger.debug("Sending confirmation email!!!");
			SesService.sendConfirmationEmailUsingSendGrid(mail.getFrom());
		} catch (JsonProcessingException ex) {
			logException(logger, ex);
		} catch (IOException ex) {
			logException(logger, ex);
		}
		return mail;
	}

	@RequestMapping(value = "/rest/email/test",
					headers = "content-type=multipart/form-data",
					consumes = {"multipart/form-data", "text/plain", "application/*"},
					method = RequestMethod.POST)
	public @ResponseBody String test (@ModelAttribute Email mail) throws IOException {
			logger.debug("Sending to DynamoDB queue manager");

			mail = getTestMail(mail);

			QueueServicesClient client = new QueueServicesClient();
			client.postToEmailInboundQueue(mail);
			
		return "welcome";
	}

	private Email getTestMail(Email mail) {
		mail.setFrom("osupa@adepanko.com");
			mail.setTo("osupa@adepanko.com");
			mail.setSubject("Test subject " + getCurrentTimeAsyyyyMMddDashHHmmss());

			mail.setText("Hard coded test!!!");
			mail.setBody(mail.getText());
			mail.setReceivedTime(new Date());

			mail.setAttachment1(null);

			return mail;
	}
}
