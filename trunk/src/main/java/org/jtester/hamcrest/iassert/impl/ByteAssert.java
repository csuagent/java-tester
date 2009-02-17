package org.jtester.hamcrest.iassert.impl;

import org.jtester.hamcrest.iassert.IAssert;
import org.jtester.hamcrest.iassert.IByteAssert;

public class ByteAssert extends BaseAssert<Byte, IByteAssert> implements
		IByteAssert {

	public ByteAssert(Byte value, Class<? extends IAssert<?, ?>> clazE) {
		super(value, clazE);
	}

	public ByteAssert(Class<? extends IAssert<?, ?>> clazE) {
		super(clazE);
	}

	public ByteAssert(Class<Byte> clazT, Class<? extends IAssert<?, ?>> clazE) {
		super(clazT, clazE);
	}

}
