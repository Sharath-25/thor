package com.xworkz.cm.dto;

import java.io.Serializable;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.log4j.Logger;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RegisterDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(RegisterDTO.class);

	@NotNull()
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
	@NotNull
	private String course;

	@AssertTrue()
	@NotNull
	private Boolean agree;

	public RegisterDTO() {
		super();
		logger.info(this.getClass().getSimpleName() + "\t Object Created");
	}

}
