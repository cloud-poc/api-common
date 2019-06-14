package org.akj.springboot.common.exception;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BusinessException extends RuntimeException implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7160449079887288990L;

	private String message = null;

	private String code = null;

	public BusinessException(String code, String message) {
		super(message);
		this.code = code;
	}

	public BusinessException(String message, String code, Throwable e) {
		super(message, e);
		this.message = message;
		this.code = code;
	}

}
