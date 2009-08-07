package org.jtester.fortest.hibernate;

import org.jtester.dbtest.bean.User;

public interface UserService extends BaseService<User> {
	public String findAddress();

	public User getUser(int id);
}
