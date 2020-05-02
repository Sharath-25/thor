package com.xworkz.cm.service;

import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.xworkz.cm.dao.LoginDAO;
import com.xworkz.cm.dto.LoginDTO;
import com.xworkz.cm.entity.RegisterEntity;
import com.xworkz.cm.exception.LoginException;

@Component
public class LoginServiceImpl implements LoginService {
	private static Integer noOfLoginAttempt = 0;
	@Autowired
	private LoginDAO loginDAO;
	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final Logger logger = Logger.getLogger(LoginServiceImpl.class);

	public LoginServiceImpl() {
		super();
		logger.info(this.getClass().getSimpleName() + "\t Object Created");
	}

	@Override
	public String getRegisterEntity(LoginDTO loginDTO) throws LoginException {
		logger.info("invoked LoginServiceImpl getRegisterEntity()");
		boolean flag = false;
		RegisterEntity registerEntity = null;
		try {
			registerEntity = this.loginDAO.getRegisterEntity(loginDTO.getEmail());
			logger.info(registerEntity.toString());

			if (Objects.isNull(registerEntity)) {
				return "loginFailed";
			}

			String randomPassword = registerEntity.getRandomPassword();
			noOfLoginAttempt = registerEntity.getNoOfLoginAttempt();
			if (noOfLoginAttempt < 3 && noOfLoginAttempt >= 0) {

				if (passwordEncoder.matches(loginDTO.getRandomPassword(), randomPassword)) {
					logger.info("password is correct");
					flag = true;
				} else {
					noOfLoginAttempt++;
					this.loginDAO.updateLoginCount(noOfLoginAttempt, registerEntity.getRegID());
				}
			} else {
				if (noOfLoginAttempt >= 3) {

					return "block";
				}
			}

			if (flag == true) {
				return "loginSucess";
			}
		} catch (LoginException e) {
			logger.error(e.getMessage(), e);
			throw new LoginException("some problem in login");
		}

		return "loginFailed";
	}

}
