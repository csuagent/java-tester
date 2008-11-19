package org.jtester.hamcrest.impl;

import org.jtester.hamcrest.IAssert;
import org.jtester.hamcrest.IFloatAssert;

public class FloatAssert extends NumberAssert<Float, IFloatAssert> implements IFloatAssert {

	public FloatAssert(Class<? extends IAssert> clazE) {
		super(clazE);
	}

	public FloatAssert(Class<Float> clazT, Class<? extends IAssert> clazE) {
		super(clazT, clazE);
	}

	public FloatAssert(Float value, Class<? extends IAssert> clazE) {
		super(value, clazE);
	}

}
