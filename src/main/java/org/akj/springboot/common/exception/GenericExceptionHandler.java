package org.akj.springboot.common.exception;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.akj.springboot.common.constant.CustomHttpStatus;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GenericExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public BaseResponse handleException(Exception ex, HttpServletResponse httpServletResponse) {
		log.error("method:{},message:{}", "handleException", ex);
		if (ex instanceof MissingServletRequestParameterException || ex instanceof ServletRequestBindingException
				|| ex instanceof TypeMismatchException || ex instanceof HttpMessageNotReadableException
				|| ex instanceof MethodArgumentNotValidException || ex instanceof MissingServletRequestPartException
				|| ex instanceof BindException || ex instanceof ConstraintViolationException) {

			httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());

			BaseResponse response = new BaseResponse();
			ResponseInfo responseInfo = new ResponseInfo();

			responseInfo.setCode("ERROR-001");
			responseInfo.setMessage(ex.getMessage());
			response.setStatus(httpServletResponse.getStatus());
			response.setResponseInfo(responseInfo);

			return response;
		} else if (ex instanceof HttpMediaTypeNotSupportedException
				|| ex instanceof HttpMediaTypeNotAcceptableException) {
			httpServletResponse.setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());

			BaseResponse response = new BaseResponse();
			ResponseInfo responseInfo = new ResponseInfo();

			responseInfo.setCode("ERROR-002");
			responseInfo.setMessage(ex.getMessage());
			response.setStatus(httpServletResponse.getStatus());
			response.setResponseInfo(responseInfo);

			return response;
		} else if (ex instanceof NoHandlerFoundException) {
			httpServletResponse.setStatus(HttpStatus.NOT_FOUND.value());

			BaseResponse response = new BaseResponse();
			ResponseInfo responseInfo = new ResponseInfo();

			responseInfo.setCode("ERROR-003");
			responseInfo.setMessage(ex.getMessage());
			response.setStatus(httpServletResponse.getStatus());
			response.setResponseInfo(responseInfo);

			return response;
		} else {
			httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

			BaseResponse response = new BaseResponse();
			ResponseInfo responseInfo = new ResponseInfo();

			responseInfo.setCode("ERROR-000");
			responseInfo.setMessage(ex.getMessage());
			response.setStatus(httpServletResponse.getStatus());
			response.setResponseInfo(responseInfo);

			return response;
		}
	}

	@ExceptionHandler(TechnicalException.class)
	@ResponseBody
	public BaseResponse handleException(TechnicalException ex, HttpServletResponse httpServletResponse) {
		httpServletResponse.setStatus(CustomHttpStatus.TECHNICAL_EXCEPTION.value());

		BaseResponse response = new BaseResponse();
		ResponseInfo responseInfo = new ResponseInfo();
		responseInfo.setCode(ex.getCode());
		responseInfo.setMessage(ex.getMessage());
		
		response.setStatus(httpServletResponse.getStatus());
		response.setResponseInfo(responseInfo);

		return response;
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public BaseResponse handleException(BusinessException ex, HttpServletResponse httpServletResponse) {
		httpServletResponse.setStatus(CustomHttpStatus.BUSINESS_EXCEPTION.value());

		BaseResponse response = new BaseResponse();
		
		ResponseInfo responseInfo = new ResponseInfo();
		responseInfo.setCode(ex.getCode());
		responseInfo.setMessage(ex.getMessage());
		
		response.setStatus(httpServletResponse.getStatus());
		response.setResponseInfo(responseInfo);

		return response;
	}
	
}
