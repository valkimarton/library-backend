package hu.bme.szoftarch.library.libbackend.utils;

import hu.bme.szoftarch.library.libbackend.utils.exceptions.BadRequestException;
import hu.bme.szoftarch.library.libbackend.utils.exceptions.LibraryException;
import hu.bme.szoftarch.library.libbackend.utils.exceptions.OutOfResourceException;
import hu.bme.szoftarch.library.libbackend.utils.exceptions.UnauthenticatedUserException;
import org.modelmapper.MappingException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ExceptionHandlerGlobal extends ResponseEntityExceptionHandler {
    @ExceptionHandler({LibraryException.class})
    protected ResponseEntity<Object> handleLibraryException(RuntimeException e, WebRequest request) {
        e.printStackTrace();
        return handleExceptionInternal(e, e.getMessage(),
                null,
                HttpStatus.BAD_REQUEST, request);   // TODO: create more exceptions, and add handlers for each.
    }

    @ExceptionHandler({OutOfResourceException.class})
    protected ResponseEntity<Object> handleOutOfResourceException(RuntimeException e, WebRequest request) {
        e.printStackTrace();
        return handleExceptionInternal(e, e.getMessage(),
                null,
                HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({UnauthenticatedUserException.class})
    protected ResponseEntity<Object> handleUnauthenticatedUserException(RuntimeException e, WebRequest request) {
        e.printStackTrace();
        return handleExceptionInternal(e, e.getMessage(),
                null,
                HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException e, WebRequest request) {
        e.printStackTrace();
        return handleExceptionInternal(e, e.getMessage(),
                null,
                HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({BadRequestException.class})
    protected ResponseEntity<Object> handleBadRequestException(RuntimeException e, WebRequest request) {
        e.printStackTrace();
        return handleExceptionInternal(e, e.getMessage(),
                null,
                HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({MappingException.class})
    protected ResponseEntity<Object> handleMappingException(RuntimeException e, WebRequest request) {
        e.printStackTrace();
        return handleExceptionInternal(e, "Requested entity is not found",
                null,
                HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    protected ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException e, WebRequest request) {
        e.printStackTrace();
        return handleExceptionInternal(e, e.getMessage(),
                null,
                HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleAllException(Exception e, WebRequest request) {
        e.printStackTrace();
        return handleExceptionInternal(e, e.getMessage(),
                null,
                HttpStatus.CONFLICT, request);
    }
}
