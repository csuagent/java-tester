package org.jtester.tutorial02.badcase;

import java.util.ArrayList;
import java.util.List;

public class Invoice {
	private List<LineItem> lineItems = null;
	private Customer customer;

	public Invoice(Customer customer) {
		this.lineItems = new ArrayList<LineItem>();
		this.customer = customer;
	}

	public void addItemQuantity(Product product, int quantity) {
		LineItem lineItem = new LineItem();
		lineItem.setInv(this);
		lineItem.setPercentDiscount(this.customer.getPercentDiscount());
		lineItem.setProd(product);
		lineItem.setQuantity(quantity);
		lineItem.setUnitPrice(product.getUnitPrice());

		lineItems.add(lineItem);
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}
}
