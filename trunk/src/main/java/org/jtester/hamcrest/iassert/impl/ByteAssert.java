package org.jtester.hamcrest.iassert.impl;

import org.jtester.hamcrest.iassert.IByteAssert;
import org.jtester.hamcrest.iassert.common.ICommonAssert;

public class ByteAssert extends BaseAssert<Byte, IByteAssert> implements
		IByteAssert {

	public ByteAssert(Byte value, Class<? extends ICommonAssert<?, ?>> clazE) {
		super(value, clazE);
	}

	public ByteAssert(Class<? extends ICommonAssert<?, ?>> clazE) {
		super(clazE);
	}

	public ByteAssert(Class<Byte> clazT, Class<? extends ICommonAssert<?, ?>> clazE) {
		super(clazT, clazE);
	}

}
