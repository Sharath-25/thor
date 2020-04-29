package com.xworkz.cm.dao;

import com.xworkz.cm.entity.RegisterEntity;

public interface ForgotPasswordDAO {
	RegisterEntity getEmail(String email);

	int resetPasswordAndLoginCount(int noOfLoginAttempt, String resetPassword, int rID);
}
