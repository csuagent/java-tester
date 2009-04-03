package org.jtester.hamcrest.iassert.object;

import org.jtester.hamcrest.iassert.common.IAssert;

public interface ICharacterAssert extends IAssert<Character, ICharacterAssert> {
	public ICharacterAssert is(char ch);
}
