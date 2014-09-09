package com.parssoft.documentmanager.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.S3Link;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.parssoft.documentmanager.utils.EmailPostUtils;
import com.parssoft.documentmanager.utils.GenericUtilities;
import java.io.Serializable;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 * This is our email and metadata bean.  It represents
 * a single received email with its attachment
 * along with its corresponding metadata
 * 
 * Created on Jul 16, 2014, 11:29:42 AM
 *
 * @author Ade
 *
 * Copyright(c) 2014 ParsSoft.  All Rights Reserved.
 * This software is the proprietary information of ParsSoft.
 *
 */

@Component
@DynamoDBTable(tableName = EmailPostUtils.DYNAMO_DB_TABLE_NAME_EMAIL)
@JsonIgnoreProperties({"file", "s3OriginalFile", "s3OcrFile",
	"uploadStartTime", "uploadEndTime",
	"timeSentToOcrQueue", "timePulledFromOcrQueue", "timeOcrWsCalled",
	"timeOcrWsResponded", "ocrReponseDocumentName", "ocrReponseDocumentPath",
	"timeEmailSent", "uploadIpAddress",
	"attachment1", "attachment2", "attachment3",
	"attachmentInfo", "attachments", "text"})
public class Email extends EmailLight implements Serializable {

    private Integer customerNumber;
	private Integer orderNumber;

	/*
	{"customerNumber":12,
	"orderNumber":5,
	"fileName":"1Overview.pdf",
	"fileSize":150,
	"s3OriginalFileLink":"https://parssoft-docman-uploads.s3.amazonaws.com/1Overview.pdf",
	"subject":"Subject of the message",
	"from":"rahat.jaan@gmail.com",
	"to":"admin@parssoft.com",
	"body":"Test Body 2"}

	*/

	private Long fileSize;

	private String s3OriginalFileLink;
	private S3Link s3OriginalFile;
	private String s3OcrFileLink;
	private S3Link s3OcrFile;

	private Date uploadStartTime;
	private Date uploadEndTime;

	private Date timeSentToOcrQueue;
	private Date timePulledFromOcrQueue;
	private Date timeOcrWsCalled;
	private Date timeOcrWsResponded;
	private String ocrReponseDocumentName;
	private String ocrReponseDocumentPath;
	private Date timeEmailSent;

	private String uploadIpAddress;
	
	public Email() {}

	public Email(Integer customerNumber, Integer orderNumber, String emailAddress,
			String fileName, Long fileSize, String s3OriginalFileLink, S3Link s3OriginalFile,
			String s3OcrFileLink, S3Link s3OcrFile, Date uploadStartTime, Date uploadEndTime,
			Date timeSentToOcrQueue, Date timePulledFromOcrQueue, Date timeOcrWsCalled,
			Date timeOcrWsResponded, String ocrReponseDocumentName, String ocrReponseDocumentPath,
			Date timeEmailSent, String uploadIpAddress) {
		this.customerNumber = customerNumber;
		this.orderNumber = orderNumber;
		this.fileSize = fileSize;
		this.s3OriginalFileLink = s3OriginalFileLink;
		this.s3OriginalFile = s3OriginalFile;
		this.s3OcrFileLink = s3OcrFileLink;
		this.s3OcrFile = s3OcrFile;
		this.uploadStartTime = uploadStartTime;
		this.uploadEndTime = uploadEndTime;
		this.timeSentToOcrQueue = timeSentToOcrQueue;
		this.timePulledFromOcrQueue = timePulledFromOcrQueue;
		this.timeOcrWsCalled = timeOcrWsCalled;
		this.timeOcrWsResponded = timeOcrWsResponded;
		this.ocrReponseDocumentName = ocrReponseDocumentName;
		this.ocrReponseDocumentPath = ocrReponseDocumentPath;
		this.timeEmailSent = timeEmailSent;
		this.uploadIpAddress = uploadIpAddress;
	}

	@DynamoDBAttribute(attributeName = "customer_number")
	public Integer getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}

	@DynamoDBAttribute(attributeName = "order_number")
	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	@DynamoDBAttribute(attributeName = "file_size")
	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

//	@DynamoDBAttribute(attributeName = "s3_original_file_link")
	public String getS3OriginalFileLink() {
		return s3OriginalFileLink;
	}

	public void setS3OriginalFileLink(String s3OriginalFileLink) {
		this.s3OriginalFileLink = s3OriginalFileLink;
	}

//	@DynamoDBAttribute(attributeName = "s3_original_file")
	public S3Link getS3OriginalFile() {
		return s3OriginalFile;
	}

	public void setS3OriginalFile(S3Link s3OriginalFile) {
		this.s3OriginalFile = s3OriginalFile;
	}

	@DynamoDBAttribute(attributeName = "s3_ocr_file_link")
	public String getS3OcrFileLink() {
		return s3OcrFileLink;
	}

	public void setS3OcrFileLink(String s3OcrFileLink) {
		this.s3OcrFileLink = s3OcrFileLink;
	}

		@DynamoDBAttribute(attributeName = "s3_ocr_file")
	public S3Link getS3OcrFile() {
		return s3OcrFile;
	}

	public void setS3OcrFile(S3Link s3OcrFile) {
		this.s3OcrFile = s3OcrFile;
	}

	@DynamoDBAttribute(attributeName = "upload_start_time")
	public Date getUploadStartTime() {
		return uploadStartTime;
	}

	public void setUploadStartTime(Date uploadStartTime) {
		this.uploadStartTime = uploadStartTime;
	}

	@DynamoDBAttribute(attributeName = "upload_end_time")
	public Date getUploadEndTime() {
		return uploadEndTime;
	}

	public void setUploadEndTime(Date uploadEndTime) {
		this.uploadEndTime = uploadEndTime;
	}

	@DynamoDBAttribute(attributeName = "time_sent_to_ocr_queue")
	public Date getTimeSentToOcrQueue() {
		return timeSentToOcrQueue;
	}

	public void setTimeSentToOcrQueue(Date timeSentToOcrQueue) {
		this.timeSentToOcrQueue = timeSentToOcrQueue;
	}

	@DynamoDBAttribute(attributeName = "time_pulled_from_ocr_queue")
	public Date getTimePulledFromOcrQueue() {
		return timePulledFromOcrQueue;
	}

	public void setTimePulledFromOcrQueue(Date timePulledFromOcrQueue) {
		this.timePulledFromOcrQueue = timePulledFromOcrQueue;
	}

	@DynamoDBAttribute(attributeName = "time_ocr_ws_called")
	public Date getTimeOcrWsCalled() {
		return timeOcrWsCalled;
	}

	public void setTimeOcrWsCalled(Date timeOcrWsCalled) {
		this.timeOcrWsCalled = timeOcrWsCalled;
	}

	@DynamoDBAttribute(attributeName = "time_ocr_ws_responded")
	public Date getTimeOcrWsResponded() {
		return timeOcrWsResponded;
	}

	public void setTimeOcrWsResponded(Date timeOcrWsResponded) {
		this.timeOcrWsResponded = timeOcrWsResponded;
	}

	@DynamoDBAttribute(attributeName = "ocr_response_document_name")
	public String getOcrReponseDocumentName() {
		return ocrReponseDocumentName;
	}

	public void setOcrReponseDocumentName(String ocrReponseDocumentName) {
		this.ocrReponseDocumentName = ocrReponseDocumentName;
	}

	@DynamoDBAttribute(attributeName = "ocr_response_document_path")
	public String getOcrReponseDocumentPath() {
		return ocrReponseDocumentPath;
	}

	public void setOcrReponseDocumentPath(String ocrReponseDocumentPath) {
		this.ocrReponseDocumentPath = ocrReponseDocumentPath;
	}

	@DynamoDBAttribute(attributeName = "time_email_sent")
	public Date getTimeEmailSent() {
		return timeEmailSent;
	}

	public void setTimeEmailSent(Date timeEmailSent) {
		this.timeEmailSent = timeEmailSent;
	}


	@DynamoDBAttribute(attributeName = "upload_ip_address")
	public String getUploadIpAddress() {
		return uploadIpAddress;
	}

	public void setUploadIpAddress(String uploadIpAddress) {
		this.uploadIpAddress = uploadIpAddress;
	}

	/**
	 * It appears that you can programmatically create a table
	 * in DynamoDB using an object instance and making
	 * sure that all properties values are not null, otherwise,
	 * it will skip null fields
	 *
	 * @param mapper
	 * @return
	 */
	public Email createTablePrep(DynamoDBMapper mapper) {
		customerNumber = 11;
		orderNumber = 22;

		fileSize = 33l;

		s3OriginalFileLink = "file name";
//		s3OriginalFile = mapper.createS3Link(DocumentManagerUtils.S3_OCR_RESPONSE_DOCUMENTS_DIR,
//			DocumentManagerUtils.LOCAL_FILE_STORE.concat("/dm-init.png"));

		s3OcrFileLink = "ocr file";
//		s3OcrFile = mapper.createS3Link(DocumentManagerUtils.S3_OCR_RESPONSE_DOCUMENTS_DIR,
//			DocumentManagerUtils.LOCAL_FILE_STORE.concat("/dm-init.png"));

		uploadStartTime = GenericUtilities.getCurrentTimestamp();
		uploadEndTime = GenericUtilities.getCurrentTimestamp();

		timeSentToOcrQueue = GenericUtilities.getCurrentTimestamp();
		timePulledFromOcrQueue = GenericUtilities.getCurrentTimestamp();
		timeOcrWsCalled = GenericUtilities.getCurrentTimestamp();
		timeOcrWsResponded = GenericUtilities.getCurrentTimestamp();
		ocrReponseDocumentName = "name";
		ocrReponseDocumentPath = "path";
		timeEmailSent = GenericUtilities.getCurrentTimestamp();

		uploadIpAddress = "192.168.0.0";

		return this;
	}

}
