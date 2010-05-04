package org.jtester.tutorial.daos;

import java.util.List;

import org.jtester.tutorial.beans.PhoneItem;

public interface PhoneItemDao {
	PhoneItem findPhoneByName(String username);

	PhoneItem findPhoneByMobile(String mobile);

	long insertPhoneItem(PhoneItem phoneItem);

	List<PhoneItem> findPageBook(int page, int pageSize);

	long getPhoneItemIdByName(String userName);
}
