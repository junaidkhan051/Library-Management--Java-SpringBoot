package com.LibraryManagement.LibraryManagement.exception;

import com.LibraryManagement.LibraryManagement.entity.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<apiError> handleBookNotFound(BookNotFoundException ex) {

        apiError error = new apiError(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }
}