package org.jtester.hamcrest.bean;

@SuppressWarnings("unused")
public class Address {
	private String street;

	private String sn;

	private String name;

	public Address(String street, String sn, String name) {
		this.street = street;
		this.sn = sn;
		this.name = name;
	}
}
