package com.shockn745.domain.exceptions;

public class IllegalMoveException extends Exception {

    public IllegalMoveException() {
    }

    public IllegalMoveException(String detailMessage) {
        super(detailMessage);
    }

    public IllegalMoveException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
