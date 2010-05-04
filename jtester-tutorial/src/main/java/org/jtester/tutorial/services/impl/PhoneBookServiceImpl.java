package org.jtester.tutorial.services.impl;

import java.util.List;

import org.jtester.tutorial.beans.PhoneItem;
import org.jtester.tutorial.daos.PhoneGroupDao;
import org.jtester.tutorial.daos.PhoneItemDao;
import org.jtester.tutorial.services.PhoneBookService;

public class PhoneBookServiceImpl implements PhoneBookService {
	private PhoneItemDao phoneItemDao;

	private PhoneGroupDao phoneGroupDao;

	public void setPhoneItemDao(PhoneItemDao phoneItemDao) {
		this.phoneItemDao = phoneItemDao;
	}

	public void setPhoneGroupDao(PhoneGroupDao phoneGroupDao) {
		this.phoneGroupDao = phoneGroupDao;
	}

	public List<PhoneItem> findPageBook(int page, int pageSize) {
		return this.phoneItemDao.findPageBook(page, pageSize);
	}

	public PhoneItem findPhoneByMobile(String mobile) {
		return this.phoneItemDao.findPhoneByMobile(mobile);
	}

	public PhoneItem findPhoneByName(String username) {
		return this.phoneItemDao.findPhoneByName(username);
	}

	public List<PhoneItem> findPhoneItemsByGroupName(String groupName) {
		return this.phoneGroupDao.findPhoneItemsByGroupName(groupName);
	}

	public void insertPhoneBook(String username, String mobile, String groupname) {
		PhoneItem phoneItem = new PhoneItem();
		// TODO Auto-generated method stub

		long itemId = this.phoneItemDao.insertPhoneItem(phoneItem);
		// PhoneGroup phoneGroup= new PhoneGroup();
		System.out.println(itemId);

	}
}
