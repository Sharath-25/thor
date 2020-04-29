package com.xworkz.cm.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ForgotPasswordDTO {

	@Email(message = "Invalid email")
	@NotNull
	@NotEmpty(message = "enter an email")
	private String email;

	public ForgotPasswordDTO() {
		super();
		System.out.println("Created\t" + this.getClass().getSimpleName());
	}

}
