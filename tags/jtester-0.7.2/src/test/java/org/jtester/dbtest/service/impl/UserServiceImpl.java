package org.jtester.dbtest.service.impl;

import org.jtester.dbtest.bean.User;
import org.jtester.dbtest.service.AddressService;
import org.jtester.dbtest.service.UserService;

public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	private AddressService addressService;

	public AddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	public String findAddress() {
		return this.addressService.findAddress();
	}

	public User getUser(int id) {
		return this.getBeanById(id);
	}
}
