package org.jtester.jmock;

import org.hamcrest.Matcher;
import org.jmock.syntax.MethodClause;
import org.jmock.syntax.ReceiverClause;

public interface ICallMethod {
	public <T> T oneOf(T mockObject);

	public <T> T one(T mockObject);

	public <T> T allowing(T mockObject);

	public <T> T ignoring(T mockObject);

	public MethodClause ignoring(Matcher<?> mockObjectMatcher);

	public <T> T never(T mockObject);

	public ReceiverClause atLeast(int count);

	public ReceiverClause between(int minCount, int maxCount);

	public ReceiverClause atMost(int count);

	public MethodClause allowing(Matcher<?> mockObjectMatcher);

	public ReceiverClause exactly(int count);
}
