package com.parssoft.documentmanager.aws.utils;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.parssoft.documentmanager.utils.EmailPostUtils;
import com.amazonaws.auth.AWSCredentialsProvider;
import java.text.MessageFormat;

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
	private static AWSCredentialsProvider credentialsProvider = null;

	static AWSCredentials credentials;

	protected AwsCredentialsHandler() {
		try {
			credentials =
					new ProfileCredentialsProvider(
							EmailPostUtils.AWS_PROFILES_CREDENTIALS_PATH,
							EmailPostUtils.AWS_PROFILE_NAME).getCredentials();
//			credentialsProvider = 
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

	public final static AWSCredentials getCredentials() {
		if (instance == null) {
			instance = new AwsCredentialsHandler();
		}
		return credentials;
	}

}
