package org.jtester.hamcrest.iassert.impl;

import org.jtester.hamcrest.iassert.object.IObjectAssert;

public class ObjectAssert extends ReflectionAssert<Object, IObjectAssert>
		implements IObjectAssert {
	public ObjectAssert() {
		super(IObjectAssert.class);
	}

	public ObjectAssert(Object bean) {
		super(bean, IObjectAssert.class);
	}
}
