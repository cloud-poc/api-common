package org.akj.springboot.common.constant;

public enum CustomHttpStatus {
	TECHNICAL_EXCEPTION(409, "custom:technical exception"), BUSINESS_EXCEPTION(500, "custom:business exception");

	private final int value;

	private final String reasonPhrase;

	CustomHttpStatus(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	/**
	 * Return the integer value of this status code.
	 */
	public int value() {
		return this.value;
	}

	/**
	 * Return the reason phrase of this status code.
	 */
	public String getReasonPhrase() {
		return this.reasonPhrase;
	}
}
