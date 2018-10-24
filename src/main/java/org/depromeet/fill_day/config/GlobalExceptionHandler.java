package org.depromeet.fill_day.config;

import org.depromeet.fill_day.domain.dto.ErrorDTO;
import org.depromeet.fill_day.exception.AuthenticationFailureException;
import org.depromeet.fill_day.exception.NoAccountFoundException;
import org.depromeet.fill_day.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(value=HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorDTO> handleMethodNotAllowedException(HttpRequestMethodNotSupportedException e) {
		ErrorDTO response = new ErrorDTO("지원하지 않는 메소드입니다. 메소드를 다시 한번 확인해주세요.");
		return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(value=HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<ErrorDTO> handleMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
		ErrorDTO response = new ErrorDTO("지원하지 않는 미디어 타입입니다. 미디어 타입을 다시 한번 확인해주세요.");
		logger.error(e.getMessage(), e.getCause());
		return new ResponseEntity<>(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler({
			NoAccountFoundException.class,
			AuthenticationFailureException.class
	})
	public ResponseEntity<ErrorDTO> handleUnauthorizedException(AuthenticationException e){
		ErrorDTO response = new ErrorDTO("로그인이 필요합니다.");
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler({
			NotFoundException.class
	})
	public ResponseEntity<ErrorDTO> handleNotFoundException(NotFoundException e){
		ErrorDTO response = new ErrorDTO("해당 리소스를 찾을 수 없습니다.");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}
