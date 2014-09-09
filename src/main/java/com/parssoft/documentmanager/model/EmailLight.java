package com.parssoft.documentmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 *
 * Created on Aug 12, 2014, 12:12:07 AM
 *
 * @author Ade Panko <ade@adepanko.com>
 *
 * Copyright(c) 2014  Copyright(c) 2014 ParsSoft. All Rights Reserved.
 * This software is the proprietary information of ParsSoft.
 *
 */

@JsonIgnoreProperties({
//	"attachment1", "attachment2", "attachment3", "text"
	"attachments", "attachmentInfo"})
public class EmailLight implements Serializable {
	static final long serialVersionUID = 0533534l;

    private String subject = null;
	private String from = null;
	private String to = null;
	private String body = null;
	// SendGrid uses text, however, we use body as it is "safer"
	// in the web context and move data from text to body once
	// we receive the mail message
	private String text = null;
	private String attachmentInfo = null;
	private CommonsMultipartFile attachment1 = null;
	private CommonsMultipartFile attachment2 = null;
	private CommonsMultipartFile attachment3 = null;
	private String fileName = null;

	private File[] attachments;

	private Date receivedTime = null;

	public EmailLight() {}

	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}

	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public Date getReceivedTime() {
		return receivedTime;
	}
	public void setReceivedTime(Date receivedTime) {
		this.receivedTime = receivedTime;
	}

	public File[] getAttachments() {
		return attachments;
	}
	public void setAttachments(File[] attachments) {
		this.attachments = attachments;
	}

	public String getAttachmentInfo() {
		return attachmentInfo;
	}

	public void setAttachmentInfo(String attachmentInfo) {
		this.attachmentInfo = attachmentInfo;
	}

	public CommonsMultipartFile getAttachment1() {
		return attachment1;
	}

	public void setAttachment1(CommonsMultipartFile attachment1) {
		this.attachment1 = attachment1;
	}

	public CommonsMultipartFile getAttachment2() {
		return attachment2;
	}

	public void setAttachment2(CommonsMultipartFile attachment2) {
		this.attachment2 = attachment2;
	}

	public CommonsMultipartFile getAttachment3() {
		return attachment3;
	}

	public void setAttachment3(CommonsMultipartFile attachment3) {
		this.attachment3 = attachment3;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
