package nl.griffelservices.proxy.stub;

import java.util.regex.Pattern;

/**
 * Abstract class for validating request parameters.
 * 
 * @author Frans van Gool
 */
public abstract class Parameter {
	/** the desired value of the parameter */
	private String desiredValue;

	/**
	 * Constructs a StringParameter object.
	 * 
	 * @param desiredValue
	 *            the desired value of the parameter
	 */
	public Parameter(String desiredValue) {
		this.desiredValue = desiredValue;
	}

	/**
	 * Returns the desired value of the parameter
	 * 
	 * @return the desired value of the parameter
	 */
	public String getDesiredValue() {
		return desiredValue;
	}

	/**
	 * Tests whether the desired and actual values match.
	 * 
	 * @param actualValue
	 *            the actual value
	 * @return whether the desired and actual values match.
	 */
	public abstract boolean matches(Object actualValue);

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "\t" + this.desiredValue;
	}

	/**
	 * In this parameter implementation, the actual and desired values match if
	 * the string representation of the actual value is equal to the desired
	 * value.
	 * 
	 * @author Frans van Gool
	 */
	public static class EqualityParameter extends Parameter {
		/**
		 * Constructs a EqualityParameter object.
		 * 
		 * @param desiredValue
		 *            the desired value of the parameter
		 */
		public EqualityParameter(String desiredValue) {
			super(desiredValue);
		}

		public boolean matches(Object actualValue) {
			return actualValue != null && getDesiredValue().equals(actualValue.toString());
		}
	}

	/**
	 * In this parameter implementation, the actual and desired values match if
	 * the string representation of the actualvalue matches the desired value
	 * regular expression.
	 * 
	 * @author Frans van Gool
	 */
	public static class RegexParameter extends Parameter {
		/**
		 * Constructs a RegexParameter object.
		 * 
		 * @param desiredValue
		 *            the desired value of the parameter
		 */
		public RegexParameter(String desiredValue) {
			super(desiredValue);
		}

		public boolean matches(Object actualValue) {
			return actualValue != null && Pattern.matches(getDesiredValue(), actualValue.toString());
		}
	}
}
