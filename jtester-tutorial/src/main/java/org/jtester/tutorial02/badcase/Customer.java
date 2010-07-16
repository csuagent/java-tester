package org.jtester.tutorial02.badcase;

public class Customer {
	private int percentDiscount;

	public Customer(int i, String firstname, String lastname, int discount, Address billingAddress,
			Address shippingAddress) {
		this.percentDiscount = discount;
	}

	public int getPercentDiscount() {
		return percentDiscount;
	}

}
