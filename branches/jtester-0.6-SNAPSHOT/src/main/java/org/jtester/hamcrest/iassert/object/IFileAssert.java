package org.jtester.hamcrest.iassert.object;

import java.io.File;

import org.jtester.hamcrest.iassert.common.IBaseAssert;

public interface IFileAssert extends IBaseAssert<File, IFileAssert> {
	/**
	 * the specified file exists
	 * 
	 * @return
	 */
	public IFileAssert isExists();

	/**
	 * the specified file doesn't exists
	 * 
	 * @return
	 */
	public IFileAssert unExists();
}
