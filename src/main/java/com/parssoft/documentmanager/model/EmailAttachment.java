package com.parssoft.documentmanager.model;

import java.io.Serializable;

/**
 *
 * Created on Sep 13, 2014, 8:07:35 PM
 *
 * @author Ade
 *
 * Copyright(c) 2014  Copyright(c) 2014 ParsSoft. All Rights Reserved.
 * This software is the proprietary information of ParsSoft.
 *
 */
public class EmailAttachment implements Serializable {

	static final long serialVersionUID = 100002l;

	private String filename;
	private Long fileSize;
	private String s3OriginalFileLink;
	private String bucketName;
	private String version;

	public EmailAttachment() {}

	public EmailAttachment(String filename, Long fileSize,
			String s3OriginalFileLink, String bucketName, String version) {
		this.filename = filename;
		this.fileSize = fileSize;
		this.s3OriginalFileLink = s3OriginalFileLink;
		this.bucketName = bucketName;
		this.version = version;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getS3OriginalFileLink() {
		return s3OriginalFileLink;
	}

	public void setS3OriginalFileLink(String s3OriginalFileLink) {
		this.s3OriginalFileLink = s3OriginalFileLink;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
