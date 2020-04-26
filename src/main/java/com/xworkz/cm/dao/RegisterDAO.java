package com.xworkz.cm.dao;

import com.xworkz.cm.entity.RegisterEntity;

public interface RegisterDAO {

	String save(RegisterEntity registerEntity);

	String checkUserId(RegisterEntity registerEntity);

	String checkEmail(RegisterEntity registerEntity);
}
