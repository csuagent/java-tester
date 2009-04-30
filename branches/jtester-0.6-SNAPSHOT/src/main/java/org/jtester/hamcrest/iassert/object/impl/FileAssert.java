package org.jtester.hamcrest.iassert.object.impl;

import java.io.File;

import org.jtester.hamcrest.iassert.common.impl.BaseAssert;
import org.jtester.hamcrest.iassert.object.IFileAssert;
import org.jtester.hamcrest.matcher.FileExistsMatcher;
import org.jtester.hamcrest.matcher.FileExistsMatcher.FileExistsMatcherType;

public class FileAssert extends BaseAssert<File, IFileAssert> implements IFileAssert {
	public FileAssert() {
		super(IFileAssert.class);
	}

	public FileAssert(File file) {
		super(file, IFileAssert.class);
	}

	public IFileAssert isExists() {
		FileExistsMatcher matcher = new FileExistsMatcher(this.value, FileExistsMatcherType.ISEXISTS);
		return this.assertThat(matcher);
	}

	public IFileAssert unExists() {
		FileExistsMatcher matcher = new FileExistsMatcher(this.value, FileExistsMatcherType.UNEXISTS);
		return this.assertThat(matcher);
	}
}
