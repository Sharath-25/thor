package com.xworkz.cm.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.log4j.Logger;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(LoginDTO.class);

	@NotEmpty()
	@Email()
	@NotNull
	private String email;

	@NotEmpty()
	@Size(min = 8, max = 8)
	@NotNull
	private String randomPassword;

	public LoginDTO() {
		super();
		logger.info(this.getClass().getSimpleName() + "\t Object Created");
	}

}
