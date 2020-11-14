package com.yamiy.hospsys.exceptions;

import java.util.ArrayList;
import java.util.List;

public class BadRequestException extends BaseException {
	private static final long serialVersionUID = 1L;
	private List<String> errorMessages;

	private BadRequestException(List<String> errorMessages) {
		super();
		this.errorMessages = errorMessages;
	}

	public static BadRequestException createWith(List<String> errorMessages) {
		return new BadRequestException(errorMessages);
	}

	public static BadRequestException createWith(String errorMessage) {
		List<String> l = new ArrayList<String>(1);
		l.add(errorMessage);
		return new BadRequestException(l);
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}
}
