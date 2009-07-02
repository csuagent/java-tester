package org.jtester.jmock.service;

public class CallingService {
	private CalledService calledService;

	public void call(String message) {
		this.calledService.called(message);
	}

	public CalledService getCalledService() {
		return calledService;
	}

	public void setCalledService(CalledService calledService) {
		this.calledService = calledService;
	}
}
