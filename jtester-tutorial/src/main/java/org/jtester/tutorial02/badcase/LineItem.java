package org.jtester.tutorial02.badcase;

import java.math.BigDecimal;

public class LineItem {
	private Invoice inv;
	private Product prod;
	private int quantity;
	private int percentDiscount;
	private double unitPrice;
	private BigDecimal extendedPrice = null;

	public LineItem() {

	}

	public LineItem(Invoice inv, Product prod, int quantity, int percentDiscount, double unitPrice,
			BigDecimal extendedPrice) {
		this.inv = inv;
		this.prod = prod;
		this.quantity = quantity;
		this.percentDiscount = percentDiscount;
		this.unitPrice = unitPrice;
		this.extendedPrice = extendedPrice;
	}

	public Invoice getInv() {
		return inv;
	}

	public void setInv(Invoice inv) {
		this.inv = inv;
	}

	public Product getProd() {
		return prod;
	}

	public void setProd(Product prod) {
		this.prod = prod;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPercentDiscount() {
		return percentDiscount;
	}

	public void setPercentDiscount(int percentDiscount) {
		this.percentDiscount = percentDiscount;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void caculate() {
		if (extendedPrice == null) {
			double d = this.unitPrice * this.quantity * (100 - this.percentDiscount) / 100;
			String str = String.format("%.2f", d);
			this.extendedPrice = new BigDecimal(str);
		}
	}

	public BigDecimal getExtendedPrice() {
		caculate();
		return this.extendedPrice;
	}
}
