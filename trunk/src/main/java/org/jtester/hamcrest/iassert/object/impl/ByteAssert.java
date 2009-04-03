package org.jtester.hamcrest.iassert.object.impl;

import org.jtester.hamcrest.iassert.common.impl.AllAssert;
import org.jtester.hamcrest.iassert.object.IByteAssert;

public class ByteAssert extends AllAssert<Byte, IByteAssert> implements IByteAssert {

	// public ByteAssert(Byte value, Class<? extends IBaseAssert<?, ?>> clazE) {
	// super(value, clazE);
	// }
	//
	// public ByteAssert(Class<? extends IBaseAssert<?, ?>> clazE) {
	// super(clazE);
	// }

	public ByteAssert(Byte value) {
		super(value, IByteAssert.class);
	}

	public ByteAssert() {
		super(IByteAssert.class);
	}
}
