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
import com.xworkz.cm.service.LoginService;

@Component
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	public LoginController() {
		super();
		System.out.println("Created\t" + this.getClass().getSimpleName());
	}

	@GetMapping("/registerpage.do")
	public String registerPage(@ModelAttribute("registerDTO") RegisterDTO registerDTO) {
		System.out.println("Invoked registerPage()");
		return "Register";

	}

	@GetMapping("/page.do")
	public String loginpage(@ModelAttribute("loginDTO") LoginDTO loginDTO) {
		System.out.println("invoked loginpage()");
		return "Login";

	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(@Valid @ModelAttribute("loginDTO") LoginDTO loginDTO, BindingResult result, Model model) {
		System.out.println("invoked login()");
		System.out.println(loginDTO);
		if (result.hasErrors()) {
			return "Login";
		}

		String valid = this.loginService.getRegisterEntity(loginDTO);
		if (valid.equals("loginFailed")) {
			model.addAttribute("msg", "Email or password is wrong..please try again");
			return "Login";

		} else if (valid.equals("block")) {
			model.addAttribute("msg",
					"Password Tries Exceeded.Your account has been blocked due to Wrong password, have patience till I Develop forgotPassword module.");
			return "Block";
		}
		model.addAttribute("msg", "LOGIN is Successful...Have a good day");
		return "Home";

	}
}
