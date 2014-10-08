package com.parssoft.documentmanager.controller;

import com.parssoft.documentmanager.model.EmailLight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * This is a simple test interface controller.
 * 
 * Created on Aug 16, 2014, 1:18:18 PM
 *
 * @author Ade
 *
 * Copyright(c) 2014  Copyright(c) 2014 ParsSoft. All Rights Reserved.
 * This software is the proprietary information of ParsSoft.
 *
 */
@Controller
@SessionAttributes
public class WelcomeController {

	private static final Logger logger = LogManager.getLogger(WelcomeController.class);

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(ModelMap model) {

		logger.info("In welcome controller.");

		model.addAttribute("mail", new EmailLight());

		return "test";
	}
}
