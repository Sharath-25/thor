package com.xworkz.cm.service;


import com.xworkz.cm.dto.RegisterDTO;

public interface RegisterService {
	String save(RegisterDTO registerDTO);

	boolean checkUserId(RegisterDTO registerDTO);

	boolean checkEmail(RegisterDTO registerDTO);
}
