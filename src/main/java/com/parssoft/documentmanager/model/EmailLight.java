package com.parssoft.documentmanager.model;

import java.io.File;
import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * Created on Aug 12, 2014, 12:12:07 AM
 *
 * @author Ade
 *
 * Copyright(c) 2014  Copyright(c) 2014 ParsSoft. All Rights Reserved.
 * This software is the proprietary information of ParsSoft.
 *
 */

@JsonIgnoreProperties({"attachments", "attachmentInfo", "text"})
public class EmailLight implements Serializable {

	static final long serialVersionUID = 1000003l;

	private String from = null;
	private String to = null;

    private String subject = null;
	private String body = null;

	// SendGrid uses text, however, we use body as it is "safer"
	// in the web context and move data from text to body once
	// we receive the mail message
	private String text = null;
	private String attachmentInfo = null;

	private CommonsMultipartFile attachment1 = null;
	private CommonsMultipartFile attachment2 = null;
	private CommonsMultipartFile attachment3 = null;
	private CommonsMultipartFile attachment4 = null;
	private CommonsMultipartFile attachment5 = null;
	private CommonsMultipartFile attachment6 = null;
	private CommonsMultipartFile attachment7 = null;
	private CommonsMultipartFile attachment8 = null;
	private CommonsMultipartFile attachment9 = null;
	private CommonsMultipartFile attachment10 = null;

	private Date receivedTime = null;
	private File[] attachments;

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

	public CommonsMultipartFile getAttachment4() {
		return attachment4;
	}

	public void setAttachment4(CommonsMultipartFile attachment4) {
		this.attachment4 = attachment4;
	}

	public CommonsMultipartFile getAttachment5() {
		return attachment5;
	}

	public void setAttachment5(CommonsMultipartFile attachment5) {
		this.attachment5 = attachment5;
	}

	public CommonsMultipartFile getAttachment6() {
		return attachment6;
	}

	public void setAttachment6(CommonsMultipartFile attachment6) {
		this.attachment6 = attachment6;
	}

	public CommonsMultipartFile getAttachment7() {
		return attachment7;
	}

	public void setAttachment7(CommonsMultipartFile attachment7) {
		this.attachment7 = attachment7;
	}

	public CommonsMultipartFile getAttachment8() {
		return attachment8;
	}

	public void setAttachment8(CommonsMultipartFile attachment8) {
		this.attachment8 = attachment8;
	}

	public CommonsMultipartFile getAttachment9() {
		return attachment9;
	}

	public void setAttachment9(CommonsMultipartFile attachment9) {
		this.attachment9 = attachment9;
	}

	public CommonsMultipartFile getAttachment10() {
		return attachment10;
	}

	public void setAttachment10(CommonsMultipartFile attachment10) {
		this.attachment10 = attachment10;
	}	
}
