package org.jtester.fortest.service;

import java.util.List;

import org.jtester.fortest.beans.User;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public double paySalary(String postcode) {
		List<User> users = this.userDao.findUserByPostcode(postcode);
		double total = 0.0d;
		if (users == null || users.size() == 0) {
			return total;
		}
		for (User user : users) {
			total += user.getSarary();
		}
		return total;
	}

	public void insertUser(User user) {
		this.userDao.insertUser(user);
	}
}
