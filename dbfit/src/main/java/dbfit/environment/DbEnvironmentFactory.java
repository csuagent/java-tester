package dbfit.environment;

import org.jtester.dbfit.environment.DBEnvironment;

public class DbEnvironmentFactory {
	private static DBEnvironment environment;

	public static DBEnvironment getDefaultEnvironment() {
		return environment;
	}

	public static void setDefaultEnvironment(DBEnvironment newDefaultEnvironment) {
		environment = newDefaultEnvironment;
	}
}
