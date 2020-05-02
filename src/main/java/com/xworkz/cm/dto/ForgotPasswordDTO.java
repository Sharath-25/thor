package com.xworkz.cm.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ForgotPasswordDTO {

	private static final Logger logger = Logger.getLogger(ForgotPasswordDTO.class);

	@Email(message = "Invalid email")
	@NotNull
	@NotEmpty(message = "enter an email")
	private String email;

	public ForgotPasswordDTO() {
		super();
		logger.info(this.getClass().getSimpleName() + "\t Object Created");
	}

}
