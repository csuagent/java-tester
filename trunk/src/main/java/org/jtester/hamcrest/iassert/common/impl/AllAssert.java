package org.jtester.hamcrest.iassert.common.impl;

import org.jtester.hamcrest.iassert.common.IBaseAssert;

/**
 * all assert statements have been implemented by this class <br/>
 * (or it't super classes)
 * 
 * @author darui.wudr
 * 
 */
public class AllAssert<T, E extends IBaseAssert<T, ?>> extends ReflectionAssert<T, E> {
	public AllAssert(Class<? extends IBaseAssert<?, ?>> clazE) {
		super(clazE);
	}

//	public AllAssert(Class<T> clazT, Class<? extends IBaseAssert<?, ?>> clazE) {
//		super(clazT, clazE);
//	}

	public AllAssert(T value, Class<? extends IBaseAssert<?, ?>> clazE) {
		super(value, clazE);
	}
}
