package org.jtester.hamcrest.iassert.object.impl;

import org.jtester.hamcrest.iassert.common.impl.AllAssert;
import org.jtester.hamcrest.iassert.object.IByteAssert;

public class ByteAssert extends AllAssert<Byte, IByteAssert> implements IByteAssert {

	public ByteAssert(Byte value) {
		super(value, IByteAssert.class);
	}

	public ByteAssert() {
		super(IByteAssert.class);
	}
}
