package org.jtester.hamcrest.iassert.object;

import java.util.Map;

import org.jtester.hamcrest.iassert.common.IBaseAssert;

public interface IMapAssert extends IBaseAssert<Map<?, ?>, IMapAssert> {
	public IMapAssert hasKeys(Object key, Object... keys);

	public IMapAssert hasValues(Object value, Object... values);
}
