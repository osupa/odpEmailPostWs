package com.parssoft.documentmanager.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({
	"attachment1", "attachment2", "attachment3", "attachment4", "attachment5",
	"attachment6", "attachment7", "attachment8", "attachment9", "attachment10",
	"attachmentInfo", "attachments", "text"})

public class Email extends EmailLight implements Serializable {

	static final long serialVersionUID = 100001l;

    private Integer customerNumber;
	private Integer orderNumber;

	private EmailAttachment[] files;
	
	public Email() {}

	public Integer getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public EmailAttachment[] getFiles() {
		return files;
	}

	public void setFiles(EmailAttachment[] files) {
		this.files = files;
	}

}
