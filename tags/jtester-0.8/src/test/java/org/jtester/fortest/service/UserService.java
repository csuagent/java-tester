package org.jtester.fortest.service;

import org.jtester.fortest.beans.User;

public interface UserService {
	public double paySalary(String postcode);

	public void insertUser(User user);
}
