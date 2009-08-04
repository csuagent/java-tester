package org.jtester.unitils.database.ibatis.service;

import org.jtester.unitils.database.ibatis.beans.User;

public interface UserService {
	public double paySalary(String postcode);

	public void insertUser(User user);
}
