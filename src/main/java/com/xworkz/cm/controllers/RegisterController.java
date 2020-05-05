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

import com.xworkz.cm.dto.LoginDTO;
import com.xworkz.cm.dto.RegisterDTO;
import com.xworkz.cm.exception.RegisterException;
import com.xworkz.cm.service.RegisterService;

@Component
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private RegisterService registerService;

	private static final Logger logger = Logger.getLogger(RegisterController.class);

	public RegisterController() {
		super();
		logger.info(this.getClass().getSimpleName() + "\t Object Created");
	}

	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String index() {
		return "Home";

	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/loginpage.do")
	public String loginpage(@ModelAttribute("loginDTO") LoginDTO loginDTO) {
		logger.info("invoked loginpage()");
		return "Login";

	}

	@GetMapping("/page.do")
	public String registerPage(@ModelAttribute("registerDTO") RegisterDTO registerDTO, Model model) {
		logger.info("Invoked registerPage()");
		return "Register";

	}

	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("registerDTO") RegisterDTO register, BindingResult result,
			Model model) {

		logger.info("Invoked registerPage()");
		logger.info("if User ID has only white space then it will be trimmed into null |" + register.getUserId() + "|");

		if (result.hasErrors()) {
			return "Register";
		}
		try {
			boolean userId = this.registerService.checkUserId(register);
			if (userId == true) {
				model.addAttribute("msg", "User Id already exists");
				return "Register";
			}
			boolean checkEmail = this.registerService.checkEmail(register);
			if (checkEmail == true) {
				model.addAttribute("msg", "email id already exists");
				return "Register";
			}
			boolean valid = this.registerService.save(register);
			if (valid == true) {
				model.addAttribute("msg",
						"Registeration is successful and your password has been sent to this E-mail ID"
								+ register.getEmail() + ",\t kindly check out your inbox");
				return "Register";
			}
			model.addAttribute("msg", "some problem occurred in Registeration.please try again");
			return "Register";

		} catch (RegisterException e) {
			logger.error(e.getMessage(), e);
			throw new RegisterException("some problem occurred in Registeration");
		}

	}
}
