package org.jtester.hamcrest.iassert.object.impl;

import org.jtester.hamcrest.iassert.common.impl.BaseAssert;
import org.jtester.hamcrest.iassert.object.ICharacterAssert;

public class CharacterAssert extends BaseAssert<Character, ICharacterAssert>
		implements ICharacterAssert {

	public CharacterAssert() {
		super(ICharacterAssert.class);
	}

	public CharacterAssert(Character value) {
		super(value, ICharacterAssert.class);
	}

	public ICharacterAssert is(char ch) {
		return super.isEqualTo(ch);
	}
}
