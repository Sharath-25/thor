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
import com.xworkz.cm.dto.LoginDTO;
import com.xworkz.cm.dto.RegisterDTO;
import com.xworkz.cm.exception.LoginException;
import com.xworkz.cm.service.LoginService;

@Component
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	private static final Logger logger = Logger.getLogger(LoginController.class);

	public LoginController() {
		logger.info(this.getClass().getSimpleName() + "\t object created");
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@RequestMapping("/forgotPage.do")
	public String forgotPasswordPage(@ModelAttribute("forgotPasswordDTO") ForgotPasswordDTO forgotPasswordDTO) {
		logger.info("invoked forgotPasswordPage()");
		return "ForgotPassword";

	}

	@GetMapping("/registerpage.do")
	public String registerPage(@ModelAttribute("registerDTO") RegisterDTO registerDTO) {
		logger.info("Invoked registerPage()");
		return "Register";

	}

	@GetMapping("/page.do")
	public String loginpage(@ModelAttribute("loginDTO") LoginDTO loginDTO) {
		logger.info("invoked loginpage()");
		return "Login";

	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(@Valid @ModelAttribute("loginDTO") LoginDTO loginDTO, BindingResult result, Model model) {
		logger.info("invoked login()");
		logger.info(loginDTO.toString());

		if (result.hasErrors()) {
			return "Login";
		}
		try {
			String valid = this.loginService.getRegisterEntity(loginDTO);
			if (valid.equals("loginFailed")) {
				model.addAttribute("msg", "Email or password is wrong..please try again");
				return "Login";

			} else if (valid.equals("block")) {
				model.addAttribute("msg",
						"Password Tries Exceeded.Your account has been blocked due to Wrong password.");
				return "Block";
			}
			model.addAttribute("msg", "LOGIN is Successful...Have a good day");
		} catch (LoginException e) {
			logger.error(e.getMessage(), e);
			throw new LoginException("some problem in login");
		}
		return "Home";

	}

}
