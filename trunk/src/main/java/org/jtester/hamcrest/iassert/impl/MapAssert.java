package org.jtester.hamcrest.iassert.impl;

import java.util.Map;

import org.jtester.hamcrest.iassert.IMapAssert;

public class MapAssert extends BaseAssert<Map<?, ?>, IMapAssert> implements
		IMapAssert {
	public MapAssert() {
		super(IMapAssert.class);
	}

	public MapAssert(Map<?, ?> map) {
		super(map, IMapAssert.class);
	}

	// public MapAssert(Class<? extends IAssert<?, ?>> clazE) {
	// super(clazE);
	// }
	//
	// public MapAssert(Class<Map<?, ?>> clazT,
	// Class<? extends IAssert<?, ?>> clazE) {
	// super(clazT, clazE);
	// }
	//
	// public MapAssert(Map<?, ?> value, Class<? extends IAssert<?, ?>> clazE) {
	// super(value, clazE);
	// }
}
