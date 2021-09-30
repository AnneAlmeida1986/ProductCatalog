package br.com.tlf.dip.product.catalog.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.Hidden;



@Hidden
@RestControllerAdvice
public class ValidationHandler {
	
	@Autowired
	@JsonInclude(Include.NON_DEFAULT) @Hidden
	private MessageSource messageSource;	
	private int status_code;
	private String message;
	

	public int getStatus_code() {
		return status_code;
	}



	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}




	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ValidationHandler handle(MethodArgumentNotValidException exception){
		String errorMessage = null;
		int statusCode = 0;
		FieldError fe = exception.getBindingResult().getFieldError();
		
		if(fe != null) {
			errorMessage = "O campo " + fe.getField() + " " + messageSource.getMessage(fe, LocaleContextHolder.getLocale());
			statusCode = HttpStatus.BAD_REQUEST.value();				
		}
			
		ValidationHandler validationHandler = new ValidationHandler();
		validationHandler.setStatus_code(statusCode);
		validationHandler.setMessage(errorMessage);
			
		return validationHandler;		
	}

}
