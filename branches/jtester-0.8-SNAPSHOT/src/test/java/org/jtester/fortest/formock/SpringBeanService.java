package org.jtester.fortest.formock;

public interface SpringBeanService {
	public SomeInterface getDependency1();

	public SomeInterface getDependency2();

	public static class SpringBeanServiceImpl1 implements SpringBeanService {
		private SomeInterface dependency;
		private SomeInterface dependency2;

		public SpringBeanServiceImpl1() {
			super();
		}

		public SpringBeanServiceImpl1(SomeInterface dependency) {
			super();
			this.dependency = dependency;
		}

		public SomeInterface getDependency1() {
			return dependency;
		}

		public SomeInterface getDependency2() {
			return dependency2;
		}

		public void setDependency2(SomeInterface dependency2) {
			this.dependency2 = dependency2;
		}
	}

	public static class SpringBeanServiceImpl2 implements SpringBeanService {
		private SomeInterface dependency;
		private SomeInterface dependency2;

		public SpringBeanServiceImpl2() {
			super();
		}

		public SpringBeanServiceImpl2(SomeInterface dependency) {
			super();
			this.dependency = dependency;
		}

		public SomeInterface getDependency1() {
			return dependency;
		}

		public SomeInterface getDependency2() {
			return dependency2;
		}

		public void setDependency2(SomeInterface dependency2) {
			this.dependency2 = dependency2;
		}
	}
}
