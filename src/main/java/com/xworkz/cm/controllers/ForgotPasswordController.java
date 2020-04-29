package com.xworkz.cm.controllers;

import javax.validation.Valid;

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

	public ForgotPasswordController() {
		super();
		System.out.println("Created\t" + this.getClass().getSimpleName());
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/forgotPage.do")
	public String forgotPasswordPage(@ModelAttribute("forgotPasswordDTO") ForgotPasswordDTO forgotPasswordDTO) {
		System.out.println("invoked forgotPasswordPage()");
		return "ForgotPassword";

	}

	@RequestMapping(value = "/resetPassword.do", method = RequestMethod.POST)
	public String resetPassword(@Valid @ModelAttribute("forgotPasswordDTO") ForgotPasswordDTO forgotPasswordDTO,
			BindingResult result, Model model) {
		System.out.println("invoked resetPassword()");
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
			e.printStackTrace();
			throw new ForgotPasswordException(
					"some problem occurred in resetting password " + this.getClass().getSimpleName());
		}
		return "ForgotPassword";
	}

}
