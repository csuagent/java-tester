package org.jtester.fortest.hibernate;

import org.jtester.dbtest.bean.User;

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
