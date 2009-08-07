package org.jtester.unitils.database.ibatis.service;

import java.util.List;

import org.jtester.fortest.beans.User;

public interface UserDao {
	List<User> findUserByPostcode(String postcode);

	void insertUser(User user);
}
