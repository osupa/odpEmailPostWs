package com.parssoft.documentmanager.aws.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.parssoft.documentmanager.aws.utils.AwsCredentialsHandler;
import com.parssoft.documentmanager.utils.GenericUtilities;
import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class is our S3 Service layer.  It provides access to S3 for uploading document files
 * 
 * Created on Jul 17, 2014, 7:10:04 PM
 *
 * @author Ade
 *
 * Copyright(c) 2014 ParsSoft.  All Rights Reserved. This software is the proprietary information of ParsSoft
 *
 */
public class S3Service {

	private AmazonS3 s3 = null;

	private static final Logger log = LoggerFactory.getLogger(S3Service.class);

	public S3Service() {
//		if (s3 != null) {
			s3 = new AmazonS3Client(AwsCredentialsHandler.getCredentials());
//			s3.setRegion(RegionConfiguration.getRegion());
//		}
	}

	/**
	 * 
	 * @param uploadDirectory
	 * @param file
	 * @throws IOException
	 */
	public void addDocumentToS3Bucket(String uploadDirectory, File file) throws IOException {

		try {
			PutObjectResult putObject = s3.putObject(
					(new PutObjectRequest(uploadDirectory, file.getName(), file)).withCannedAcl(CannedAccessControlList.PublicRead));
		} catch (AmazonServiceException ase) {
			GenericUtilities.logAmazonServiceException(log, ase);
		}
	}

	/**
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
