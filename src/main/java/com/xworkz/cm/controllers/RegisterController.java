package com.xworkz.cm.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xworkz.cm.dto.LoginDTO;
import com.xworkz.cm.dto.RegisterDTO;
import com.xworkz.cm.service.RegisterService;

@Component
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private RegisterService registerService;

	public RegisterController() {
		super();
		System.out.println("Created\t" + this.getClass().getSimpleName());
	}

	@GetMapping("/loginpage.do")
	public String loginpage(@ModelAttribute("loginDTO") LoginDTO loginDTO) {
		System.out.println("invoked loginpage()");
		return "Login";

	}

	@GetMapping("/page.do")
	public String registerPage(@ModelAttribute("registerDTO") RegisterDTO registerDTO, Model model) {
		System.out.println("Invoked registerPage()");
		model.addAttribute("registerDTO", new RegisterDTO());
		return "Register";

	}

	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("registerDTO") RegisterDTO register, BindingResult result,
			Model model) {

		System.out.println("Invoked register()");
		if (result.hasErrors()) {
			return "Register";
		}
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
		String password = this.registerService.save(register);
		model.addAttribute("msg", "Registeration is successful and the password is:" + password);
		return "Register";

	}
}
