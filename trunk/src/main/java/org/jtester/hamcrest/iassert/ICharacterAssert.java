package org.jtester.hamcrest.iassert;

import org.jtester.hamcrest.iassert.commoninterface.ICommonAssert;

public interface ICharacterAssert extends ICommonAssert<Character, ICharacterAssert> {
	public ICharacterAssert is(char ch);
}
