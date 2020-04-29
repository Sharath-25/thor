package com.xworkz.cm.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerMethods {

	@ExceptionHandler(value = RegisterException.class)
	public String handleRegisterException(RegisterException e) {
		System.out.println(e);
		return "RegisterException";

	}

	@ExceptionHandler(value = LoginException.class)
	public String handleLoginException(LoginException e) {
		System.out.println();
		return "LoginException";

	}

	@ExceptionHandler(value = ForgotPasswordException.class)
	public String handleForgotPasswordException(ForgotPasswordException e) {
		return "ForgotPasswordException";

	}

}
