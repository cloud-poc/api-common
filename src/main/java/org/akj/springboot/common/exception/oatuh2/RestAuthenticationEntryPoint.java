package org.akj.springboot.common.exception.oatuh2;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.akj.springboot.common.exception.BaseResponse;
import org.akj.springboot.common.exception.ResponseInfo;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO this is sample handling for authException, while user requirement should
		// be more complicated than this

		BaseResponse baseResponse = new BaseResponse();
		ResponseInfo responseInfo = new ResponseInfo();

		responseInfo.setCode("ERROR-004");
		responseInfo.setMessage("Authentication error: " + authException.getMessage());
		baseResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

		OutputStream out = response.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(out, baseResponse);
		out.flush();
	}

}
