package com.parssoft.documentmanager.model;

/**
 *
 * Created on Aug 27, 2014, 7:14:34 AM
 *
 * @author Ade Panko <ade@adepanko.com>
 *
 * Copyright(c) 2014 Copyright(c) 2014 ParsSoft. All Rights Reserved. This software is the proprietary information of
 * ParsSoft.
 *
 */
public class Response {

	private int code;
	private boolean success;
	private String message;

	public Response(int code, String msg) {
		this.code = code;
		this.success = code == 200;
		this.message = msg;
	}

	public int getCode() {
		return this.code;
	}

	public boolean getStatus() {
		return this.success;
	}

	public String getMessage() {
		return this.message;
	}
}
