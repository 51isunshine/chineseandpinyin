package org.dylan.chinesepinyin.util;

public class ChinesePinYinException extends RuntimeException {

	private static final long serialVersionUID = 12132242L;
	ChinesePinYinException() {
	}

	public ChinesePinYinException(String message) {
		super(message);
	}

	public ChinesePinYinException(String message, Throwable caseMessage) {
		super(message, caseMessage);
	}
}
