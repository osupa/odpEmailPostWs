package com.parssoft.documentmanager.utils;

import com.amazonaws.AmazonServiceException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.slf4j.Logger;


/**
 *
 * Created on Jul 21, 2014, 1:06:13 PM
 *
 * @author Ade
 *
 * Copyright(c) 2014 ParsSoft. All Rights Reserved. This software is the proprietary information of ParsSoft.
 * 
 */

public class GenericUtilities {
	private static final String LEFT = "<";
	private static final String RIGHT = ">";

	/**
	 * Returns current time stamp in java.sql.Timestamp
	 *
	 * @return
	 */
	public static Timestamp getCurrentTimestamp() {
		return (new Timestamp(Calendar.getInstance().getTimeInMillis()));
	}

	/**
	 * Returns current time stamp in specified military format
	 *
	 * @return
	 */
	public static String getCurrentTimeAsyyyyMMddTHHmmss() {
		return (new SimpleDateFormat("yyyyMMdd'T'HHmmss")).format(new java.util.Date());
	}

	/**
	 * Returns current time stamp in yyyyMMdd-HHmmss format
	 * @return
	 */
	public static String getCurrentTimeAsyyyyMMddDashHHmmss() {
		return (new SimpleDateFormat ("yyyyMMdd-HHmmss")).format(new java.util.Date());
	}

	/**
	 * Performs a null and string length check
	 * @param aString
	 * @return
	 */
	// we should be using Apache Commons validator for this
	public static boolean isNullOrBlank(String aString) {
		if (aString == null || aString.length() < 1) {
			return true;
		}
		return false;
	}

	/**
	 *
	 * @param log
	 * @param ase
	 */
	public static void logAmazonServiceException(Logger log, AmazonServiceException ase) {
		log.error("Error Message:    " + ase.getMessage()
			+ " HTTP Status Code: " + ase.getStatusCode()
			+ " AWS Error Code:   " + ase.getErrorCode()
			+ " Error Type:       " + ase.getErrorType()
			+ " Request ID:       " + ase.getRequestId());
	}

	/**
	 *
	 * @param log
	 * @param ex
	 */
	public static void logException(Logger log, Exception ex) {
		log.error("Error Message:    " + ex.getMessage());
	}

	/**
	 * Convert email address with the name part to just the address -
	 * Abc <a@b.com> to a@b.com
	 * 
	 * @param address
	 * @return
	 */
	public static String emailAddressOnly(String address) {
		if ((address != null) && (address.contains(LEFT))) {
			return (address.substring(address.indexOf(LEFT) + 1,
						address.lastIndexOf(RIGHT)));
		} else {
			return address;
		}
	}
}
