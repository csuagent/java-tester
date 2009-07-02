package org.jtester.hamcrest.iassert.object.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;
import org.jtester.hamcrest.iassert.common.impl.BaseAssert;
import org.jtester.hamcrest.iassert.object.intf.IMapAssert;
import org.jtester.hamcrest.matcher.array.MapMatcher;
import org.jtester.hamcrest.matcher.array.MapMatcher.MapMatcherType;

public class MapAssert extends BaseAssert<Map<?, ?>, IMapAssert> implements IMapAssert {
	public MapAssert() {
		super(IMapAssert.class);
	}

	public MapAssert(Map<?, ?> map) {
		super(map, IMapAssert.class);
	}

	public IMapAssert hasKeys(Object key, Object... keys) {
		MapMatcher matcher1 = new MapMatcher(key, MapMatcherType.KEY);
		if (keys == null || keys.length == 0) {
			return assertThat(matcher1);
		}
		List<Matcher<?>> list = new ArrayList<Matcher<?>>();
		list.add(matcher1);
		for (Object item : keys) {
			list.add(new MapMatcher(item, MapMatcherType.KEY));
		}

		return assertThat(AllOf.allOf(list));
	}

	public IMapAssert hasValues(Object value, Object... values) {
		MapMatcher matcher1 = new MapMatcher(value, MapMatcherType.VALUE);
		if (values == null || values.length == 0) {
			return assertThat(matcher1);
		}
		List<Matcher<?>> list = new ArrayList<Matcher<?>>();
		list.add(matcher1);
		for (Object item : values) {
			list.add(new MapMatcher(item, MapMatcherType.VALUE));
		}

		return assertThat(AllOf.allOf(list));
	}
}
