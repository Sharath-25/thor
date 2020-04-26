package com.xworkz.cm.service;

import java.security.SecureRandom;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xworkz.cm.dao.RegisterDAO;
import com.xworkz.cm.dto.RegisterDTO;
import com.xworkz.cm.entity.RegisterEntity;

@Component
public class RegisterServiceImpl implements RegisterService {

	private RegisterDAO registerDAO;

	private static final int lengthOfThePassword = 8;

	public RegisterServiceImpl() {
		super();
		System.out.println("Created\t" + this.getClass().getSimpleName());

	}

	@Autowired
	public void setRegisterDAO(RegisterDAO registerDAO) {
		this.registerDAO = registerDAO;
	}

	@Override
	public String save(RegisterDTO registerDTO) {

		RegisterEntity registerEntity = new RegisterEntity();
		String randomPassword = RegisterServiceImpl.generateRandomPassword(lengthOfThePassword);
		System.out.println("random password is:" + randomPassword);
		registerEntity.setRandomPassword(randomPassword);
		registerEntity.setNoOfLoginAttempt(0);
		
		BeanUtils.copyProperties(registerDTO, registerEntity);
		String password = this.registerDAO.save(registerEntity);

		return password;
	}

	@Override
	public boolean checkUserId(RegisterDTO registerDTO) {
		boolean flag = false;
		RegisterEntity registerEntity = new RegisterEntity();
		BeanUtils.copyProperties(registerDTO, registerEntity);
		String userId = this.registerDAO.checkUserId(registerEntity);
		if (userId.equals("dataNotFound")) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean checkEmail(RegisterDTO registerDTO) {
		boolean flag = false;
		RegisterEntity registerEntity = new RegisterEntity();
		BeanUtils.copyProperties(registerDTO, registerEntity);
		String email = this.registerDAO.checkEmail(registerEntity);
		System.out.println("mail" + email);
		if (email.equals("dataNotFound")) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}

	public static String generateRandomPassword(int len) {
		// ASCII range - alphanumeric (0-9, a-z, A-Z)
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();

		// each iteration of loop choose a character randomly from the given ASCII range
		// and append it to StringBuilder instance

		for (int i = 0; i < len; i++) {
			int randomIndex = random.nextInt(chars.length());
			sb.append(chars.charAt(randomIndex));
		}

		return sb.toString();
	}

}
