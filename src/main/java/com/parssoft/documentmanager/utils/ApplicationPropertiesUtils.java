package com.parssoft.documentmanager.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is a generic property file reader
 * 
 * Created on Aug 5, 2014, 2:37:33 AM
 *
 * @author Ade Panko <ade@adepanko.com>
 *
 * Copyright(c) 2014 ParsSoft. All Rights Reserved. This software is the proprietary information of ParsSoft.
 *
 */
public class ApplicationPropertiesUtils {
	private static final Properties properties = new Properties();

	private transient static final Logger log = LoggerFactory.getLogger(ApplicationPropertiesUtils.class);

	static {
		// TODO - make path a system variable
		loadProperties("/home/adepanko/app-data/dm/app-settings/dm.properties");
//		loadProperties("/home/ubuntu/app-data/dm/app-settings/dm.properties");
	}

	private static void loadProperties(String fileName) {
		try {
			Properties p = new Properties();
			p.load(new FileInputStream(fileName));

			Enumeration<?> enums = p.propertyNames();
			while (enums.hasMoreElements()) {
				String key = enums.nextElement().toString();
				properties.put(key, p.getProperty(key));
				System.out.println("key = " + key);
				System.out.println("p.getProperty(key) = " + p.getProperty(key));
			}
		} catch (IOException e) {
			log.error("Can not find profiles.properties file on classpath:", e);
		}
	}

	public static String getProperty(String key) {
		String result = properties.getProperty(key);
		return result;
	}

	public static String getValue(String key) {
		return getProperty(key);
	}

	public static int getIntVal(String key) {
		return Integer.parseInt(getProperty(key));
	}

	public static double getDoubleVal(String key) {
		return Double.parseDouble(getProperty(key));
	}

	public static boolean getBooleanVal(String key) {
		return Boolean.parseBoolean(getProperty(key));
	}

	public static String getValue(String key, String defValue) {
		String retValue = getValue(key);
		if (retValue == null) {
			log.error(String.format("Can not find property %s in profiles.properties,"
					+ " defaulting to %s", key, defValue));
			retValue = defValue;
		}
		return retValue;
	}

	public static int getIntVal(String key, int defValue) {
		int retValue;
		try {
			retValue = Integer.parseInt(getProperty(key));
		} catch (NumberFormatException ex) {
			log.error(String.format("Can not find property %s in profiles.properties,"
					+ " defaulting to %s", key, defValue));
			retValue = defValue;
		}
		return retValue;
	}

	public static double getDoubleVal(String key, double defValue) {
		double retValue;
		try {
			retValue = Double.parseDouble(getProperty(key));
		} catch (NumberFormatException ex) {
			log.error(String.format("Can not find property %s in profiles.properties,"
					+ " defaulting to %s", key, defValue));
			retValue = defValue;
		}
		return retValue;
	}

	public static long getLongVal(String key, long defValue) {
		long retValue;
		try {
			retValue = Long.parseLong(getProperty(key));
		} catch (NumberFormatException ex) {
			log.error(String.format("Can not find property %s in profiles.properties,"
					+ " defaulting to %s", key, defValue));
			retValue = defValue;
		}
		return retValue;
	}

	public static boolean getBooleanVal(String key, boolean defValue) {
		boolean retValue;
		String propValue = getProperty(key);
		if (propValue == null || propValue.isEmpty()) {
			log.error(String.format("Can not find property %s in profiles.properties,"
					+ " defaulting to %s", key, defValue));
			retValue = defValue;
		} else {
			retValue = Boolean.parseBoolean(propValue);
		}
		return retValue;
	}
}