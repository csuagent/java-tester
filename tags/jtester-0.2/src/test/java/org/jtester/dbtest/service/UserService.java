package org.jtester.dbtest.service;

import org.jtester.dbtest.bean.User;

public interface UserService extends BaseService<User> {
	public String findAddress();

	public User getUser(int id);
}
