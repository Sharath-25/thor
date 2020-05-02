package com.xworkz.cm.controllers;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xworkz.cm.dto.ForgotPasswordDTO;
import com.xworkz.cm.exception.ForgotPasswordException;
import com.xworkz.cm.service.ForgotPasswordService;

@Component
@RequestMapping("/login")
public class ForgotPasswordController {

	@Autowired
	private ForgotPasswordService forgotPasswordService;

	private static final Logger logger = Logger.getLogger(ForgotPasswordController.class);

	public ForgotPasswordController() {
		super();
		logger.info(this.getClass().getSimpleName() + "\t object created");
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/forgotPage.do")
	public String forgotPasswordPage(@ModelAttribute("forgotPasswordDTO") ForgotPasswordDTO forgotPasswordDTO) {
		logger.info("invoked forgotPasswordPage()");
		return "ForgotPassword";

	}

	@RequestMapping(value = "/resetPassword.do", method = RequestMethod.POST)
	public String resetPassword(@Valid @ModelAttribute("forgotPasswordDTO") ForgotPasswordDTO forgotPasswordDTO,
			BindingResult result, Model model) {
		logger.info("invoked resetPassword()");
		try {
			if (result.hasErrors()) {
				return "ForgotPassword";
			}
			String checkEmail = this.forgotPasswordService.getEmail(forgotPasswordDTO.getEmail());
			if (checkEmail.equalsIgnoreCase("noEmail")) {
				model.addAttribute("message", "this email Id does not exist");
				return "ForgotPassword";
			}
			model.addAttribute("message", "New password is :" + checkEmail + "  use this password to login again");
		} catch (ForgotPasswordException e) {
			logger.error(e.getMessage(), e);
			throw new ForgotPasswordException(
					"some problem occurred in resetting password " + this.getClass().getSimpleName());
		}
		return "ForgotPassword";
	}

}
