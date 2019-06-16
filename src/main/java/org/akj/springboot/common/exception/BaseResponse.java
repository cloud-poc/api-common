package org.akj.springboot.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
	// http status code
	private int status;

	//error response info
	private ResponseInfo responseInfo;
}
