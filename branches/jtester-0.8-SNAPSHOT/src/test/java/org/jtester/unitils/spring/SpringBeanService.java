package org.jtester.unitils.spring;

public class SpringBeanService {
	private SomeInterface dependency;
	private SomeInterface dependency2;

	public SpringBeanService() {
		super();
	}

	public SpringBeanService(SomeInterface dependency) {
		super();
		this.dependency = dependency;
	}

	public SomeInterface getDependency() {
		return dependency;
	}

	public SomeInterface getDependency2() {
		return dependency2;
	}

	public void setDependency2(SomeInterface dependency2) {
		this.dependency2 = dependency2;
	}

	public static interface SomeInterface {
	}

	public static class SomeInterfaceImpl implements SomeInterface {
	}

	public static class SomeInterfaceImpl2 implements SomeInterface {
	}
}
