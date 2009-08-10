package org.jtester.fortest.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class ComplexObject {
	private String name;

	private double sarary;

	private Date date;

	private Serializable phoneNumber;

	private User user;

	private Collection<String> addresses;

	private Map<String, Serializable> map;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSarary() {
		return sarary;
	}

	public void setSarary(double sarary) {
		this.sarary = sarary;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Serializable getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Serializable phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Collection<String> getAddresses() {
		return addresses;
	}

	public void setAddresses(Collection<String> addresses) {
		this.addresses = addresses;
	}

	public Map<String, Serializable> getMap() {
		return map;
	}

	public void setMap(Map<String, Serializable> map) {
		this.map = map;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("name=" + this.name);
		buffer.append("\n");
		buffer.append("sarary=" + this.sarary);
		buffer.append("\n");
		buffer.append("addresses=" + this.addresses.toString());
		return buffer.toString();
	}
}
