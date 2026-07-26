package com.LibraryManagement.LibraryManagement.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }

}
