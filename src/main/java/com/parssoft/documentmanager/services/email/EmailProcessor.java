package com.parssoft.documentmanager.services.email;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.parssoft.documentmanager.aws.services.S3Service;
import com.parssoft.documentmanager.aws.utils.RegionConfiguration;
import com.parssoft.documentmanager.model.Email;
import com.parssoft.documentmanager.model.EmailAttachment;
import com.parssoft.documentmanager.rest.services.QueueServicesClient;
import com.parssoft.documentmanager.utils.EmailPostUtils;
import static com.parssoft.documentmanager.utils.GenericUtilities.logException;
import static com.parssoft.documentmanager.utils.EmailPostUtils.URL_PART_PROTOCOL;
import static com.parssoft.documentmanager.utils.EmailPostUtils.SLASH;
import com.parssoft.documentmanager.utils.GenericUtilities;
import com.parssoft.documentmanager.utils.JSonUtility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * This class is used to process an inbound email and its associated payloads/attachments
 *
 * Created on Sep 13, 2014, 2:54:03 PM
 *
 * @author Ade
 *
 * Copyright(c) 2014 Copyright(c) 2014 ParsSoft. All Rights Reserved. This software is the proprietary information of
 * ParsSoft.
 *
 */
public class EmailProcessor {

	private final static Logger logger = LogManager.getLogger(EmailProcessor.class);
	
	public EmailProcessor() {}

	/**
	 * Processes inbound email with or without attachments
	 *
	 * @param mail
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public Email process(Email mail) throws IOException, IllegalStateException {
		// make the SendGrid text field our body
		if (mail.getText() != null) {
			mail.setBody(mail.getText());
		}
		mail.setReceivedTime(new Date());
		
		// clean email addresses
		mail.setFrom(GenericUtilities.emailAddressOnly(mail.getFrom()));
		mail.setTo(GenericUtilities.emailAddressOnly(mail.getTo()));

		// process attachments
		if ((mail.getAttachments() != null) && (mail.getAttachments().length > 0)) {
			logger.info("There is an attachment in this email.");

			this.processAttachments(mail);
			logger.info("Json string from inbound email = " + JSonUtility.convertBeanToJsonString(mail));
		}

		try {
// old workflow
//			logger.debug("Sending to sqs queue at " + getCurrentTimeAsyyyyMMddDashHHmmss());
//			SqsService sqsService = new SqsService();
//			sqsService.addMailDataToInboundQueue(JSonUtility.convertBeanToJsonString(mail));
// old workflow

			logger.debug("Sending to DynamoDB based queue manager");
			QueueServicesClient queueServicesClient = new QueueServicesClient();
			queueServicesClient.postToEmailInboundQueue(mail);

			logger.debug("Sending confirmation email!!!");
			SendGrid.sendConfirmationEmailUsingSendGrid(mail.getFrom());
		} catch (JsonProcessingException ex) {
			logException(logger, ex);
		} catch (IOException ex) {
			logException(logger, ex);
		}

		return mail;
	}

	/**
	 * Process each attachment
	 *
	 * @param mail
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	private void processAttachments(Email mail)
			throws IOException, IllegalStateException {
		File localFile = null;

		List<CommonsMultipartFile> attachments = new ArrayList<>();
		getAttachment(mail.getAttachment1(), attachments);
		getAttachment(mail.getAttachment2(), attachments);
		getAttachment(mail.getAttachment3(), attachments);
		getAttachment(mail.getAttachment4(), attachments);
		getAttachment(mail.getAttachment5(), attachments);
		getAttachment(mail.getAttachment6(), attachments);
		getAttachment(mail.getAttachment7(), attachments);
		getAttachment(mail.getAttachment8(), attachments);
		getAttachment(mail.getAttachment9(), attachments);
		getAttachment(mail.getAttachment10(), attachments);

		EmailAttachment[] emailAttachments = new EmailAttachment[attachments.size()];
		int i = 0;
		for (CommonsMultipartFile attachment : attachments) {
			S3Service s3Service = new S3Service();

			localFile = new File(EmailPostUtils.LOCAL_FILE_STORE
					+ attachment.getOriginalFilename());
			attachment.transferTo(localFile);
			logger.info("Attachment saved locally as: " + localFile.getName());

			logger.info("Sending attachment to S3.");
			String fileVersionId = s3Service.addDocumentToS3Bucket(
					EmailPostUtils.S3_EMAIL_DOCUMENT_UPLOAD_DIR, localFile);

			emailAttachments[i++] = new EmailAttachment(
					attachment.getFileItem().getName(), attachment.getSize(),
					URL_PART_PROTOCOL
						+ RegionConfiguration.getRegionDomainAddress()
						+ SLASH
						+ EmailPostUtils.S3_EMAIL_DOCUMENT_UPLOAD_DIR
						+ (SLASH + attachment.getFileItem().getName()),
					EmailPostUtils.S3_EMAIL_DOCUMENT_UPLOAD_DIR, fileVersionId);

			// cleanup
			localFile.delete();
		}

		mail.setFiles(emailAttachments);
	}

	/**
	 * Use to create attachment arraylist
	 * 
	 * @param file
	 * @param attachments
	 */
	private void getAttachment(CommonsMultipartFile file, List<CommonsMultipartFile> attachments) {
		if (file != null) {
			attachments.add(file);
		}
	}
}