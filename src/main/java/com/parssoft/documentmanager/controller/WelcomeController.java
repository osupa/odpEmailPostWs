package com.parssoft.documentmanager.controller;

import com.parssoft.documentmanager.model.EmailLight;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * Created on Aug 16, 2014, 1:18:18 PM
 *
 * @author Ade Panko <ade@adepanko.com>
 *
 * Copyright(c) 2014  Copyright(c) 2014 ParsSoft. All Rights Reserved.
 * This software is the proprietary information of ParsSoft.
 *
 */
@Controller
@SessionAttributes
public class WelcomeController {

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(ModelMap model) {

		System.out.println("I'm in welcome!!");

		model.addAttribute("mail", new EmailLight());

//		return "sendGrid";
		return "test";
	}
}
