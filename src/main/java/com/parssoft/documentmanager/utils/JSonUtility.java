package com.parssoft.documentmanager.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.parssoft.documentmanager.model.Email;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class provides simple object to JSon utilities
 *
 * Created on Jul 17, 2014, 10:25:20 PM
 *
 * @author Ade
 *
 * Copyright(c) 2014 ParsSoft.  All Rights Reserved.
 * This software is the proprietary information of ParsSoft.
 *
 */

public final class JSonUtility {

	static final ObjectMapper mapper = new ObjectMapper();
	private static final Logger log = LogManager.getLogger(JSonUtility.class.getName());

	public JSonUtility() {
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
	}

	/**
	 * 
	 * @param mail
	 * @return
	 * @throws JsonProcessingException
	 */
	public static final String convertBeanToJsonString(Email mail)
			throws JsonProcessingException {

		JsonFactory factory = new JsonFactory();
		try {
			return mapper.writeValueAsString(mail);
		} catch (JsonProcessingException ex) {
			log.debug("Json error.", ex);
			throw ex;
		}
	}

	/**
	 *
	 * @param classType
	 * @param json
	 * @return
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public static final Object convertJsonToBean(Class<?> classType, String json)
			throws JsonProcessingException, IOException {

		Object obj;
		try {
			obj = mapper.readValue(json, classType);
			return (obj);
		} catch (JsonProcessingException ex) {
			log.debug("Json error.", ex);
			throw ex;
		}
	}

}
