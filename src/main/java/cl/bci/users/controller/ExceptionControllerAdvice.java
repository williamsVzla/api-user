package cl.bci.users.controller;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.bci.users.entity.Message;
import cl.bci.users.exception.MessageCatalogue;
import cl.bci.users.exception.UserException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ExceptionControllerAdvice {
	
	@ExceptionHandler(JsonParseException.class)
	public ResponseEntity<Message> errorRequest(HttpServletRequest req, Exception ex) {

		MessageCatalogue errorCatalogue = MessageCatalogue.ERROR_JSON;
		creaLogError(req.getRequestURL().toString(), errorCatalogue.getMessage(), ex);
		return new ResponseEntity<>(createErrorByCod(errorCatalogue), errorCatalogue.getHttpStatus());
	}

	@ExceptionHandler(UserException.class)
	public ResponseEntity<Message> errorValidacion(HttpServletRequest req, UserException ex) {

		MessageCatalogue errorCatalogue = MessageCatalogue.getCode(ex.getMessage());
		creaLogError(req.getRequestURL().toString(), errorCatalogue.getMessage(), ex);
		return new ResponseEntity<>(createErrorByCod(errorCatalogue), errorCatalogue.getHttpStatus());
	}

	private static Message createErrorByCod(MessageCatalogue errorCatalogue) {
		return new Message(errorCatalogue.getMessage());
	}

	private static void creaLogError(String requestUrl, String errorInternalCode, Exception ex) {
		log.error("{} {} {}", requestUrl, errorInternalCode, ex.toString());
	}

}
