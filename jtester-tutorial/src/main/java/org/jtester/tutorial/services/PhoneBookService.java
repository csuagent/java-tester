package org.jtester.tutorial.services;

import java.util.List;

import org.jtester.tutorial.beans.PhoneItem;

public interface PhoneBookService {
	PhoneItem findPhoneByName(String username);

	PhoneItem findPhoneByMobile(String mobile);

	void insertPhoneBook(String username, String mobile, String groupname);

	List<PhoneItem> findPageBook(int page, int pageSize);

	List<PhoneItem> findPhoneItemsByGroupName(String groupName);
}
