package org.jtester.utility.beans;

public class Manager extends Employee {
	private static final long serialVersionUID = 843725563822394654L;
	private Employee Secretary;

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

}
