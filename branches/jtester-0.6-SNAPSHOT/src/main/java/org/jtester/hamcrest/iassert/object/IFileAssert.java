package org.jtester.hamcrest.iassert.object;

import java.io.File;

import org.jtester.hamcrest.iassert.common.IBaseAssert;

/**
 * 文件类型对象断言接口
 * 
 * @author darui.wudr
 * 
 */
public interface IFileAssert extends IBaseAssert<File, IFileAssert> {
	/**
	 * 断言指定的文件已经存在
	 * 
	 * @return
	 */
	public IFileAssert isExists();

	/**
	 * 断言指定的文件不存在
	 * 
	 * @return
	 */
	public IFileAssert unExists();
}
