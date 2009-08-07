package org.jtester.unitils.database.ibatis.service;

import java.sql.SQLException;
import java.util.List;

import org.jtester.beans.User;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class UserDaoImpl extends SqlMapClientDaoSupport implements UserDao {

	@SuppressWarnings("unchecked")
	public List<User> findUserByPostcode(String postcode) {
		try {
			List users = this.getSqlMapClient().queryForList("TDD_COMMON.query_users_by_postcode", postcode);
			return users;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void insertUser(User user) {
		try {
			this.getSqlMapClient().insert("TDD_COMMON.insert_user", user);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
