package org.jtester.fortest.beans;

public class Address {
	private String street;

	private String postcode;

	private String name;

	public Address(String street) {
		this.street = street;
	}

	public Address(String street, String postcode, String name) {
		this.street = street;
		this.postcode = postcode;
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
