package org.jtester.hamcrest.iassert.object.impl;

import java.io.File;

import org.jtester.hamcrest.iassert.common.impl.BaseAssert;
import org.jtester.hamcrest.iassert.object.IFileAssert;

public class FileAssert extends BaseAssert<File, IFileAssert> implements IFileAssert {
	public FileAssert() {
		super(IFileAssert.class);
	}

	public FileAssert(File file) {
		super(file, IFileAssert.class);
	}

	public IFileAssert isExists() {
		assert this.value.exists() == true : String.format("file '%s' doesn't exists", this.value.getAbsolutePath());
		return this;
	}

	public IFileAssert unExists() {
		assert this.value.exists() == false : String.format("file '%s' exists", this.value.getAbsolutePath());
		return this;
	}

}
