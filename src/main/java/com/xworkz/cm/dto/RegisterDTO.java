package com.xworkz.cm.dto;

import java.io.Serializable;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RegisterDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@NotEmpty()
	@Size(min = 4, max = 20)
	private String userId;

	@NotEmpty()
	@Email()
	private String email;

	@NotEmpty()
	@Pattern(regexp = "(^$|[0-9]{10})")
	@NotNull
	private String phone;

	@NotEmpty()
	private String course;

	@NotNull
	@AssertTrue()
	private Boolean agree;

	public RegisterDTO() {
		super();
		System.out.println("Created\t" + this.getClass().getSimpleName());
	}

}
