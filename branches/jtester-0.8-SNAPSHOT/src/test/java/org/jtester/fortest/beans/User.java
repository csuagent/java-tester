package org.jtester.fortest.beans;

public class User implements java.io.Serializable {
	private static final long serialVersionUID = 1145595282412714496L;
	private long id;
	private String first;
	private String last;

	private String postcode;
	private double sarary;

	private Address address;

	public User() {
	}

	public User(long id) {
		this.id = id;
	}

	public User(String first) {
		this.first = first;
	}

	public User(String first, String last) {
		this.first = first;
		this.last = last;
	}

	public User(long id, String first, String last) {
		this.id = id;
		this.first = first;
		this.last = last;
	}

	public User(long id, double sarary) {
		this.id = id;
		this.sarary = sarary;
	}

	public User(String first, String last, Address address) {
		this.first = first;
		this.last = last;
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public double getSarary() {
		return sarary;
	}

	public void setSarary(double sarary) {
		this.sarary = sarary;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	// public boolean equals(Object object) {
	// if (object instanceof User) {
	// return id == ((User) object).id;
	// }
	// return false;
	// }
}
