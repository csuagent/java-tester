package org.jtester.hamcrest.iassert.impl;

import org.jtester.hamcrest.iassert.IAssert;
import org.jtester.hamcrest.iassert.ICharacterAssert;

public class CharacterAssert extends BaseAssert<Character, ICharacterAssert> implements ICharacterAssert {

	public CharacterAssert(Character value, Class<? extends IAssert> clazE) {
		super(value, clazE);
	}

	public CharacterAssert(Class<? extends IAssert> clazE) {
		super(clazE);
	}

	public CharacterAssert(Class<Character> clazT, Class<? extends IAssert> clazE) {
		super(clazT, clazE);
	}

	public ICharacterAssert is(char ch) {
		return super.isEqualTo(ch);
	}
}
