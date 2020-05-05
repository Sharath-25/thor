package com.xworkz.cm.dao;

import com.xworkz.cm.entity.RegisterEntity;

public interface ForgotPasswordDAO {
	RegisterEntity getEmail(String email);

	boolean resetPasswordAndLoginCount(int noOfLoginAttempt, String resetPassword, int rID);
}
