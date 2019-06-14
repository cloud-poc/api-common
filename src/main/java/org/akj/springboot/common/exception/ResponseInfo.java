package org.akj.springboot.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseInfo {
	
	private String code = "000";
	private String message;

}
