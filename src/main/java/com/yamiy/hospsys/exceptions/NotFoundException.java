package com.yamiy.hospsys.exceptions;

public class NotFoundException extends BaseException {
	private static final long serialVersionUID = 1L;

	private final String resourceId;

	public NotFoundException(String resourceIdText, String resourceId) {
		super("Resource '" + resourceIdText + "' not found");
		this.resourceId = resourceId;
	}

	public NotFoundException(String resourceIdText) {
		super("Resource '" + resourceIdText + "' not found");
		this.resourceId = null;
	}

	public String getResourceId() {
		return resourceId;
	}
}
