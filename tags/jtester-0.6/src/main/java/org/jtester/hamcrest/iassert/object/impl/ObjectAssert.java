package org.jtester.hamcrest.iassert.object.impl;

import org.jtester.hamcrest.iassert.common.impl.ReflectionAssert;
import org.jtester.hamcrest.iassert.object.intf.IObjectAssert;

public class ObjectAssert extends ReflectionAssert<Object, IObjectAssert>
		implements IObjectAssert {
	public ObjectAssert() {
		super(IObjectAssert.class);
	}

	public ObjectAssert(Object bean) {
		super(bean, IObjectAssert.class);
	}
}
