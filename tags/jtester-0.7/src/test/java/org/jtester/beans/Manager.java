package org.jtester.beans;

import java.io.Serializable;

public class Manager extends Employee {
	private static final long serialVersionUID = 843725563822394654L;
	private Employee Secretary;

	private Serializable phoneNumber;

	public Manager() {
		super();
	}

	public Manager(String name, double sarary) {
		super(name, sarary);
	}

	public Employee getSecretary() {
		return Secretary;
	}

	public void setSecretary(Employee secretary) {
		Secretary = secretary;
	}

	public Serializable getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Serializable phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
