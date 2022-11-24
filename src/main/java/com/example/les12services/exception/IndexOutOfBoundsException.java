package com.example.les12services.exception;

import java.io.Serial;

public class IndexOutOfBoundsException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public IndexOutOfBoundsException() {
        super();
    }

    public IndexOutOfBoundsException(String message) {
        super(message);
    }
}
