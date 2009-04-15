package org.jtester.hamcrest.iassert.object;

import org.jtester.hamcrest.iassert.common.IBaseAssert;

public interface ICharacterAssert extends IBaseAssert<Character, ICharacterAssert> {
	public ICharacterAssert is(char ch);
}
