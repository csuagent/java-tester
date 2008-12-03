package org.jtester.dbtest.service.impl;

import org.jtester.dbtest.service.AddressService;
import org.jtester.dbtest.service.UserService;

public class UserServiceImpl implements UserService {
	private AddressService addressService;

	public AddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}
}
