package net.usachev.xmlwork.exception;

public class AppException extends RuntimeException {

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable e) {
        super(message, e);
    }
}
