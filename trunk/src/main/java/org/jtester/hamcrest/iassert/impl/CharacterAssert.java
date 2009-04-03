package org.jtester.hamcrest.iassert.impl;

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
