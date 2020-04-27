package com.xworkz.cm.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
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

	public LoginServiceImpl() {
		super();
		System.out.println("Created\t" + this.getClass().getSimpleName());
	}

	@Override
	public String getRegisterEntity(LoginDTO loginDTO) throws LoginException {
		System.out.println("invoked getRegisterEntity()");
		boolean flag = false;
		RegisterEntity registerEntity = null;
		try {
			registerEntity = this.loginDAO.getRegisterEntity(loginDTO.getEmail());
			System.out.println(registerEntity);

			if (Objects.isNull(registerEntity)) {
				return "loginFailed";
			}

			String randomPassword = registerEntity.getRandomPassword();
			noOfLoginAttempt = registerEntity.getNoOfLoginAttempt();
			if (noOfLoginAttempt < 3 && noOfLoginAttempt >= 0) {

				if (loginDTO.getRandomPassword().equals(randomPassword)) {
					System.out.println("password is correct");
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
			e.printStackTrace();
			throw new LoginException("some problem in login");
		}

		return "loginFailed";
	}

}
