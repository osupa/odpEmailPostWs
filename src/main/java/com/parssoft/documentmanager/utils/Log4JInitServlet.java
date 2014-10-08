package com.parssoft.documentmanager.utils;

//import javax.servlet.http.HttpServlet;

//import org.apache.log4j.BasicConfigurator;
//import org.apache.log4j.PropertyConfigurator;

/**
 *
 * Created on Oct 6, 2014, 11:01:47 PM
 *
 * @author Ade Panko <ade@adepanko.com>
 *
 * Copyright(c) 2014  Copyright(c) 2014 ParsSoft. All Rights Reserved.
 * This software is the proprietary information of ParsSoft.
 *
 */

public class Log4JInitServlet { // extends HttpServlet {

//	private static final long serialVersionUID = 1L;
//
//	public void init(ServletConfig config) throws ServletException {
//		System.out.println("Log4JInitServlet is initializing log4j");
//		String log4jLocation = config.getInitParameter("log4j-properties-location");
//
//		ServletContext sc = config.getServletContext();
//
//		if (log4jLocation == null) {
//			System.err.println("*** No log4j-properties-location init param, so initializing log4j with BasicConfigurator");
//			BasicConfigurator.configure();
//		} else {
//			String webAppPath = sc.getRealPath("/");
//			String log4jProp = webAppPath + log4jLocation;
//			File yoMamaYesThisSaysYoMama = new File(log4jProp);
//			if (yoMamaYesThisSaysYoMama.exists()) {
//				System.out.println("Initializing log4j with: " + log4jProp);
//				PropertyConfigurator.configure(log4jProp);
//			} else {
//				System.err.println("*** " + log4jProp + " file not found, so initializing log4j with BasicConfigurator");
//				BasicConfigurator.configure();
//			}
//		}
//		super.init(config);
//	}
}
