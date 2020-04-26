package com.xworkz.cm.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "is Required")
	@Email(message = "Enter valid emailId")
	@NotNull(message = "Email cannot be null")
	private String email;

	@NotNull(message = "password cannot be null")
	@NotEmpty(message = "is Required")
	@Size(message = "Password must be contain 8 Characters")
	private String randomPassword;

	public LoginDTO() {
		super();
		System.out.println("Created\t" + this.getClass().getSimpleName());
	}

}
