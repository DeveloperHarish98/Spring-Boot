package org.jsp.useradressbootapp.exception;


import org.jsp.useradressbootapp.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserBootAppExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ResponseStructure<String>> handleICE(InvalidCredentialsException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData("Cannot Find Credential");
		structure.setMessage(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleINFE(IdNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData("Cannot Find ID");
		structure.setMessage(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AdressNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleANFE(AdressNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData("Adress Not Found");
		structure.setMessage(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

}
