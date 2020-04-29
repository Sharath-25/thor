package com.xworkz.cm.service;

import java.security.SecureRandom;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.xworkz.cm.dao.ForgotPasswordDAO;
import com.xworkz.cm.entity.RegisterEntity;
import com.xworkz.cm.exception.ForgotPasswordException;

@Component
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

	@Autowired
	private ForgotPasswordDAO forgotPasswordDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final int lengthOfThePassword = 8;

	public ForgotPasswordServiceImpl() {
		super();
		System.out.println("Created\t" + this.getClass().getSimpleName());
	}

	@Override
	public String getEmail(String email) {
		System.out.println("Invoked getEmail() in service");
		String resetPassword = "";
		try {
			RegisterEntity registerEntity = this.forgotPasswordDAO.getEmail(email);
			if (Objects.isNull(registerEntity)) {
				return "noEmail";
			}
			resetPassword = generateRandomPassword(lengthOfThePassword);
			String encodedPassword = passwordEncoder.encode(resetPassword);
			registerEntity.setRandomPassword(encodedPassword);
			registerEntity.setNoOfLoginAttempt(0);
			int reset = this.forgotPasswordDAO.resetPasswordAndLoginCount(registerEntity.getNoOfLoginAttempt(),
					registerEntity.getRandomPassword(), registerEntity.getRegID());
			if (reset == 1) {
				return resetPassword;
			}
		} catch (ForgotPasswordException e) {
			throw new ForgotPasswordException("some problem occurred in serviceImpl");
		}

		return resetPassword;
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
