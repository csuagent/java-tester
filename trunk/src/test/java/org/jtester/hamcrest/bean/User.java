package org.jtester.hamcrest.bean;

@SuppressWarnings("unused")
public class User {

	private long id;

	private String first;

	private String last;

	private Address address;

	public User(String first) {
		this.first = first;
	}

	public User(long id, String first, String last) {
		this.id = id;
		this.first = first;
		this.last = last;
	}

	public User(String first, String last, Address address) {
		this.first = first;
		this.last = last;
		this.address = address;
	}
}
