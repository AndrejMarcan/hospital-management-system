package com.yamiy.hospsys.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.mediatype.vnderrors.VndErrors.VndError;

@SuppressWarnings("deprecation")
public class PlannerVndErrors {
	private List<VndError> errors;

	public PlannerVndErrors() {
		super();
		this.errors = new ArrayList<VndError>();
	}

	public PlannerVndErrors(String logref, String message) {
		this();
		this.errors.add(new VndError(logref, message));
	}

	public VndError addError(String logref, String message) {
		VndError result = new VndError(logref, message);
		this.errors.add(result);
		return result;
	}

	public VndError[] toErrors() {
		return this.errors.toArray(new VndError[this.errors.size()]);
	}
}
