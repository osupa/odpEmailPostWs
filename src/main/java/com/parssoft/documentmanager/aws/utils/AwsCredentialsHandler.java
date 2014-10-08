package com.parssoft.documentmanager.aws.utils;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.parssoft.documentmanager.utils.EmailPostUtils;
import com.parssoft.documentmanager.utils.GenericUtilities;
import java.text.MessageFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is our generic credentials handling class.  It allows us
 * to use the same credentials instance with AWS throughout
 * the application
 * 
 * Created on Jul 19, 2014, 9:01:39 AM
 *
 * @author Ade
 *
 * Copyright(c) 2014 ParsSoft.  All Rights Reserved.
 * This software is the proprietary information of ParsSoft.
 *
 */
public final class AwsCredentialsHandler {

	private static AwsCredentialsHandler instance = null;
	private static final Logger log = LogManager.getLogger(AwsCredentialsHandler.class);

	static AWSCredentials credentials;

	protected AwsCredentialsHandler() {
		try {
			credentials =
					new ProfileCredentialsProvider(
							EmailPostUtils.AWS_PROFILES_CREDENTIALS_PATH,
							EmailPostUtils.AWS_PROFILE_NAME).getCredentials();
		} catch (Exception e) {
			throw new AmazonClientException(
					MessageFormat.format("Cannot load the credentials from the"
							+ " credential profiles file. Please make sure that"
							+ " your credentials file is at the correct"
							+ " location ({0}), and is in valid format.",
							EmailPostUtils.AWS_PROFILES_CREDENTIALS_PATH),
					e);
		}
	}

	/**
	 * Return our verified credentials
	 * 
	 * @return
	 */
	public final static AWSCredentials getCredentials() {
		if (instance == null) {
			try {
				instance = new AwsCredentialsHandler();
			} catch (AmazonServiceException ase) {
				GenericUtilities.logAmazonServiceException(log, ase);
				throw ase;
			}
		}
		return credentials;
	}

}
