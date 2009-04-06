package org.jtester.hamcrest.iassert.object.impl;

import java.util.Map;

import org.jtester.hamcrest.iassert.common.impl.BaseAssert;
import org.jtester.hamcrest.iassert.object.IMapAssert;

public class MapAssert extends BaseAssert<Map<?, ?>, IMapAssert> implements IMapAssert {
	public MapAssert() {
		super(IMapAssert.class);
	}

	public MapAssert(Map<?, ?> map) {
		super(map, IMapAssert.class);
	}

	public IMapAssert hasKeys(Object key, Object... keys) {
		// TODO Auto-generated method stub
		return null;
	}

	public IMapAssert hasValues(Object value, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}
}
