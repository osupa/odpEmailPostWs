package com.parssoft.documentmanager.utils;


/**
 * This class loads application runtime properties from the dm.properties
 * file and makes them available to the application.
 * 
 * Created on Jul 25, 2014, 9:51:27 AM
 *
* @author Ade Panko <ade@adepanko.com>
 *
 * Copyright(c) 2014 ParsSoft. All Rights Reserved. This software is the proprietary information of ParsSoft.
 * 
 */

public class EmailPostUtils {

	private static final String HOME_PATH = ApplicationPropertiesUtils.getProperty("HOME_PATH");
	
	// credentials
	public static final String AWS_PROFILES_CREDENTIALS_PATH
			= HOME_PATH.concat(ApplicationPropertiesUtils.getProperty("AWS_PROFILES_CREDENTIALS_PATH"));

	public static final String AWS_PROFILE_NAME
			= ApplicationPropertiesUtils.getProperty("AWS_PROFILE_NAME");

	// region used
	public static final String regionName = ApplicationPropertiesUtils.getProperty("REGION");

	// queue paths
	public static final String SQS_IMAGE_QUEUE_NAME
			= ApplicationPropertiesUtils.getProperty("SQS_IMAGE_QUEUE_NAME");
	public static final String SQS_OCR_QUEUE_NAME
			= ApplicationPropertiesUtils.getProperty("SQS_OCR_QUEUE_NAME");
	public static final String SQS_INBOUND_EMAIL_QUEUE_NAME
			= ApplicationPropertiesUtils.getProperty("SQS_INBOUND_EMAIL_QUEUE_NAME");

	// S3 paths
	public static final String S3_USER_DOCUMENT_UPLOAD_DIR
			= ApplicationPropertiesUtils.getProperty("S3_USER_DOCUMENT_UPLOAD_DIR");
	public static final String S3_EMAIL_DOCUMENT_UPLOAD_DIR
			= ApplicationPropertiesUtils.getProperty("S3_EMAIL_DOCUMENT_UPLOAD_DIR");
	public static final String S3_OCR_RESPONSE_DOCUMENTS_DIR = S3_USER_DOCUMENT_UPLOAD_DIR.concat(
			ApplicationPropertiesUtils.getProperty("S3_OCR_RESPONSE_DOCUMENTS_DIR"));

	// local server paths
	public static final String LOCAL_FILE_STORE
			= HOME_PATH.concat(ApplicationPropertiesUtils.getProperty("LOCAL_FILE_STORE"));
	public static String LOCAL_FILE_STORE_FOR_OCR_OUTPUT = HOME_PATH.concat(
			ApplicationPropertiesUtils.getProperty("LOCAL_FILE_STORE_FOR_OCR_OUTPUT"));

	// types and extensions
	public static String OUTPUT_FILE_EXTENTION_TXT
			= ApplicationPropertiesUtils.getProperty("OUTPUT_FILE_EXTENTION_TXT");
	public static String OUTPUT_FILE_EXTENTION_DOC
			= ApplicationPropertiesUtils.getProperty("OUTPUT_FILE_EXTENTION_DOC");
	public static String OUTPUT_FILE_EXTENTION_XLS
			= ApplicationPropertiesUtils.getProperty("OUTPUT_FILE_EXTENTION_XLS");

	// email parts
	public static final String FROM = ApplicationPropertiesUtils.getProperty("FROM");
	public static final String TO_1 = ApplicationPropertiesUtils.getProperty("TO_1");
	public static final String TO_2 = ApplicationPropertiesUtils.getProperty("TO_2");
	public static final String SUBJECT = ApplicationPropertiesUtils.getProperty("SUBJECT");

	public static final String CONFIRMATION_EMAIL_SUBJECT = "Document Received.";
	public static final String CONFIRMATION_EMAIL_BODY = "Dear Customer,\n"
			+ "Thank you for submitting the order related required document via email."
			+ "  A representative will review and process your document within"
			+ " 24 to 48 hours."
			+ "\n\n\n\nThank you for your business."
			+ "\n\n\nYour Purchasing Power Team.";

	public static final long _INTERVAL_IN_SECONDS
			= ApplicationPropertiesUtils.getLongVal("_INTERVAL_IN_SECONDS", 120);

	public static final String DYNAMO_DB_TABLE_NAME_EMAIL = "DYNAMO_DB_TABLE_NAME_EMAIL";

	// queue service
	public static final String QUEUE_MANAGER_CREDENTIALS
			= ApplicationPropertiesUtils.getProperty("AUTH_UP");
	public static final String QUEUE_SERVICE_URL_AUTH
			= ApplicationPropertiesUtils.getProperty("QUEUE_SERVICE_URL_AUTH");
	public static final String QUEUE_SERVICE_URL_EMAIL
			= ApplicationPropertiesUtils.getProperty("QUEUE_SERVICE_URL_EMAIL");
	public static final String QUEUE_SERVICE_URL_DOCUMENT_WITH_PAYLOAD
			= ApplicationPropertiesUtils.getProperty("QUEUE_SERVICE_URL_DOCUMENT_WITH_PAYLOAD");
}
