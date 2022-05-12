package com.brian.desafio.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.brian.desafio.exceptions.collaborator.CollaboratorAlreadyExistsException;
import com.brian.desafio.exceptions.collaborator.CollaboratorNotFoundException;
import com.brian.desafio.exceptions.item.ItemAlreadyExistsException;
import com.brian.desafio.exceptions.item.ItemAlreadyInUseException;
import com.brian.desafio.exceptions.item.ItemNotFoundException;

@RestControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(value = CollaboratorAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public HandleMessage collaboratorAlreadyExistsException() {
		return new HandleMessage("The given collaborator already exists");
	}

	@ExceptionHandler(value = CollaboratorNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public HandleMessage collaboratorNotFoundException() {
		return new HandleMessage("The given collaborator was not founded");
	}

	@ExceptionHandler(value = ItemAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public HandleMessage itemAlreadyExistsException() {
		return new HandleMessage("The given item already exists");
	}

	@ExceptionHandler(value = ItemNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public HandleMessage itemNotFoundException() {
		return new HandleMessage("The given item was not founded");
	}

	@ExceptionHandler(value = ItemAlreadyInUseException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public HandleMessage itemAlreadyInUseException() {
		return new HandleMessage("The given item already in use by a collaborator");
	}

}
