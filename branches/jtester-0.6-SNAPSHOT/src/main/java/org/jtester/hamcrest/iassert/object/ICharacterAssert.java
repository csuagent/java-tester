package org.jtester.hamcrest.iassert.object;

import org.jtester.hamcrest.iassert.common.IBaseAssert;

/**
 * char类型对象断言接口
 * 
 * @author darui.wudr
 * 
 */
public interface ICharacterAssert extends IBaseAssert<Character, ICharacterAssert> {
	public ICharacterAssert is(char ch);
}
