package com.xworkz.cm.service;

import java.security.SecureRandom;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
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
	private MailSender mailSender;

	private static final Logger logger = Logger.getLogger(ForgotPasswordServiceImpl.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final int lengthOfThePassword = 8;

	public ForgotPasswordServiceImpl() {
		super();
		logger.info(this.getClass().getSimpleName() + "\t Object Created");
	}

	@Override
	public boolean getEmail(String email) {
		boolean flag = false;
		logger.info(" invoked ForgotPasswordServiceImpl getEmail()");
		String resetPassword = "";
		try {
			RegisterEntity registerEntity = this.forgotPasswordDAO.getEmail(email);
			if (Objects.isNull(registerEntity)) {
				return flag;
			}
			resetPassword = generateRandomPassword(lengthOfThePassword);
			String encodedPassword = passwordEncoder.encode(resetPassword);
			registerEntity.setRandomPassword(encodedPassword);
			registerEntity.setNoOfLoginAttempt(0);
			boolean valid = this.forgotPasswordDAO.resetPasswordAndLoginCount(registerEntity.getNoOfLoginAttempt(),
					registerEntity.getRandomPassword(), registerEntity.getRegID());
			if (valid == true) {
				flag = true;
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(email);
				mailMessage.setSubject("Regarding re-setting the password");
				mailMessage.setText("re-setting password is successful and your new password is:" + resetPassword
						+ "\t use this password to login. Thank you");
				mailSender.send(mailMessage);
				return flag;
			}
		} catch (ForgotPasswordException e) {
			logger.error(e.getMessage(), e);
			throw new ForgotPasswordException("some problem occurred in serviceImpl");
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
