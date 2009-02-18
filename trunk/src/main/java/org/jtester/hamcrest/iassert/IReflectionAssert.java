package org.jtester.hamcrest.iassert;

import org.unitils.reflectionassert.ReflectionComparatorMode;


public interface IReflectionAssert<T, E extends IAssert<?, ?>> {
	
	public E reflectionEqualTo(T expected, ReflectionComparatorMode... modes);
	

//	public E reflectionEqualTo(T expected, ReflectionComparatorMode... modes);

//	public E lenientEqualTo(List<T> expected);
//
//	public E lenientEqualTo(Collection<T> expected);
//
//	public E lenientEqualTo(T[] expected);

//	public E propertyEqualTo(String property, List<T> expected);
//
//	public E propertyEqualTo(String property, Collection<T> expected);
//
//	public E propertyEqualTo(String property, T[] expected);
//
//	public E propertyEqualTo(String property, T expected);
//	
//	public static class ReflectionAssert<T, E extends IAssert<?, ?>> implements IReflectionAssert<T, E>{
//		public E lenientEqualTo(List<T> expected) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		public E lenientEqualTo(Collection<T> expected) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		public E lenientEqualTo(T[] expected) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		public E lenientEqualTo(T expected) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		public E propertyEqualTo(String property, List<T> expected) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		public E propertyEqualTo(String property, Collection<T> expected) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		public E propertyEqualTo(String property, T[] expected) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		public E propertyEqualTo(String property, T expected) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		public E reflectionEqualTo(List<T> expected,
//				ReflectionComparatorMode... modes) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		public E reflectionEqualTo(Collection<T> expected,
//				ReflectionComparatorMode... modes) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		public E reflectionEqualTo(T[] expected,
//				ReflectionComparatorMode... modes) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		public E reflectionEqualTo(T expected,
//				ReflectionComparatorMode... modes) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//		@SuppressWarnings("unchecked")
//		public E reflectionEqualTo(T expected, ReflectionComparatorMode... modes) {
//			ReflectionAssert.assertReflectionEquals(expected, this.value, modes);
//			return (E) this;
//		}
	//
//		@SuppressWarnings("unchecked")
//		public E lenientEqualTo(T expected) {
//			ReflectionAssert.assertLenientEquals(expected, this.value);
//			return (E) this;
//		}
	//
//		@SuppressWarnings("unchecked")
//		public E propertyEqualTo(String property, T expected) {
//			// ReflectionAssert.assertPropertyLenientEquals(property, expected,
//			// this.value);
//			// Matcher<?> matcher = Is.is(expected);
//			Object _expected = ArrayConvertor.convert(expected);
//			if (_expected instanceof Object[]) {
//				_expected = ArrayConvertor.convert((Object[]) _expected);
//			}
//			Object _actual = ArrayConvertor.convert(this.value);
//			if (_actual instanceof Object[]) {
//				_actual = ArrayConvertor.convert((Object[]) _actual);
//			}
//			ReflectionAssert.assertPropertyLenientEquals(property, _expected,
//					_actual);
//			return (E) this;
//		}
//	}
}
