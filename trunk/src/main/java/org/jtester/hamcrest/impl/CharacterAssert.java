package org.jtester.hamcrest.impl;

import org.jtester.hamcrest.IAssert;
import org.jtester.hamcrest.ICharacterAssert;

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
