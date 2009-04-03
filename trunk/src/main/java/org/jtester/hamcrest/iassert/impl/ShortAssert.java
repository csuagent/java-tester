package org.jtester.hamcrest.iassert.impl;

import org.jtester.hamcrest.iassert.object.IShortAssert;

public class ShortAssert extends NumberAssert<Short, IShortAssert> implements
		IShortAssert {
	public ShortAssert() {
		super(IShortAssert.class);
	}

	public ShortAssert(Short sht) {
		super(sht, IShortAssert.class);
	}
}
