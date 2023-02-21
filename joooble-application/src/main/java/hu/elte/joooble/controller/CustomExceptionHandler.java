package hu.elte.joooble.controller;

import hu.elte.joooble.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

@ControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(value = NoHandlerFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String handleCustomException(NoHandlerFoundException ex) {
		return "not-found";
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(code = HttpStatus.BAD_GATEWAY)
	public String handleCustomException(BusinessException ex, Model model) {
		model.addAttribute("errorCode", ex.getErrorCode());
		model.addAttribute("errorMessage", ex.getErrorMessage());
		return "app-error";
	}
}

