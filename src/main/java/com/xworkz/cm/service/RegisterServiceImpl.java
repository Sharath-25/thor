package com.xworkz.cm.service;

import java.security.SecureRandom;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.xworkz.cm.dao.RegisterDAO;
import com.xworkz.cm.dto.RegisterDTO;
import com.xworkz.cm.entity.RegisterEntity;
import com.xworkz.cm.exception.RegisterException;

@Component
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private RegisterDAO registerDAO;
	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final Logger logger = Logger.getLogger(RegisterServiceImpl.class);

	private static final int lengthOfThePassword = 8;

	public RegisterServiceImpl() {
		super();
		logger.info(this.getClass().getSimpleName() + "\t Object Created");

	}

	@Override
	public String save(RegisterDTO registerDTO) throws RegisterException {
		logger.info("invoked RegisterServiceImpl save()");
		String randomPassword = null;
		try {
			RegisterEntity registerEntity = new RegisterEntity();
			randomPassword = RegisterServiceImpl.generateRandomPassword(lengthOfThePassword);
			logger.info("random password is:" + randomPassword);
			String encodedPassword = this.passwordEncoder.encode(randomPassword);
			logger.info("encrypted password is:" + encodedPassword);
			registerEntity.setRandomPassword(encodedPassword);
			registerEntity.setNoOfLoginAttempt(0);
			BeanUtils.copyProperties(registerDTO, registerEntity);
			this.registerDAO.save(registerEntity);

		} catch (RegisterException e) {
			logger.error(e.getMessage(), e);
			throw new RegisterException("some problem occurred in saving operation");

		}
		return randomPassword;
	}

	@Override
	public boolean checkUserId(RegisterDTO registerDTO) throws RegisterException {
		logger.info("invoked RegisterServiceImpl checkUserId()");
		boolean flag = false;
		try {
			RegisterEntity registerEntity = new RegisterEntity();
			BeanUtils.copyProperties(registerDTO, registerEntity);
			String userId = this.registerDAO.checkUserId(registerEntity);
			if (userId.equals("dataNotFound")) {
				flag = false;
			} else {
				flag = true;
			}
		} catch (RegisterException e) {
			logger.error(e.getMessage(), e);
			throw new RegisterException("some problem occurred in checking USER-ID");
		}
		return flag;
	}

	@Override
	public boolean checkEmail(RegisterDTO registerDTO) throws RegisterException {
		boolean flag = false;
		try {
			RegisterEntity registerEntity = new RegisterEntity();
			BeanUtils.copyProperties(registerDTO, registerEntity);
			String email = this.registerDAO.checkEmail(registerEntity);
			logger.info("email found" + email);
			if (email.equals("dataNotFound")) {
				flag = false;
			} else {
				flag = true;
			}
		} catch (RegisterException e) {
			logger.error(e.getMessage(), e);
			throw new RegisterException("some problem occurred in checking an Email");
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
