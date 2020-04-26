package com.xworkz.cm.dao;

import com.xworkz.cm.entity.RegisterEntity;

public interface LoginDAO {
	RegisterEntity getRegisterEntity(String email);

	Integer updateLoginCount(Integer noOfLoginAttempt, Integer regID);
}
