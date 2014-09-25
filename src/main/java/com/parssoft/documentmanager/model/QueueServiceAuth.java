package com.parssoft.documentmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.text.MessageFormat;

/**
 *
 * Created on Sep 5, 2014, 12:17:40 AM
 *
 * @author Ade
 *
 * Copyright(c) 2014  Copyright(c) 2014 ParsSoft. All Rights Reserved.
 * This software is the proprietary information of ParsSoft.
 *
 */
public class QueueServiceAuth implements Serializable {
	static final long serialVersionUID = 100002l;
	
    private String accessToken = null;
	private String tokenType = null;
	private long expiresIn = 0l;

	public QueueServiceAuth() {
	}

	@JsonProperty("access_token")
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@JsonProperty("token_type")
	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	@JsonProperty("expires_in")
	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}

	@Override
	public String toString() {
		return (MessageFormat.format("{0}/{1}/{2}", accessToken, tokenType, expiresIn));
	}
}
