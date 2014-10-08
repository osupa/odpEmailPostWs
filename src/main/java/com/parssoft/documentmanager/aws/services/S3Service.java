package com.parssoft.documentmanager.aws.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import static com.amazonaws.services.s3.model.ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.parssoft.documentmanager.aws.utils.AwsCredentialsHandler;
import com.parssoft.documentmanager.utils.GenericUtilities;
import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is our S3 Service layer. It provides access to S3 for uploading document files
 *
 * Created on Jul 17, 2014, 7:10:04 PM
 *
 * @author Ade
 *
 * Copyright(c) 2014 ParsSoft. All Rights Reserved. This software is the proprietary information of ParsSoft
 *
 */
public class S3Service {

	private AmazonS3 s3 = null;

	private static final Logger log = LogManager.getLogger(S3Service.class);

	/**
	 * Initialize with credentials
	 */
	public S3Service() {
		try {
			s3 = new AmazonS3Client(AwsCredentialsHandler.getCredentials());
		} catch (AmazonServiceException ase) {
			GenericUtilities.logAmazonServiceException(log, ase);
			throw ase;
		}
	}

	/**
	 * Use this method to add a document/file to a given S3 bucket
	 * We perform server side AES 256 bit encryption with each file uploaded
	 * and we request a file version ID
	 * 
	 * @param uploadBucket
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public String addDocumentToS3Bucket(String uploadBucket, File file) throws IOException {
		String fileVersionId = null;

		try {
			PutObjectRequest putRequest
					= new PutObjectRequest(uploadBucket, file.getName(), file);

			// setup server-side encryption
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setSSEAlgorithm(AES_256_SERVER_SIDE_ENCRYPTION);
			putRequest.setMetadata(objectMetadata);
			putRequest.withCannedAcl(CannedAccessControlList.AuthenticatedRead);

			PutObjectResult response = s3.putObject(putRequest);
			fileVersionId = response.getVersionId();
		} catch (AmazonServiceException ase) {
			GenericUtilities.logAmazonServiceException(log, ase);
			throw ase;
		}

		return fileVersionId;
	}


	/**
	 * Retrieve a document/file from S3
	 * 
	 * @param downloadDirectory
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public S3Object getDocumentFromS3Bucket(String downloadDirectory, String fileName)
			throws IOException {

		try {
			return (s3.getObject(downloadDirectory, fileName));
		} catch (AmazonServiceException ase) {
			GenericUtilities.logAmazonServiceException(log, ase);
		}

		return null;
	}

}
