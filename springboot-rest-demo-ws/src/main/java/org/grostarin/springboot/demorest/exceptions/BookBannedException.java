package org.grostarin.springboot.demorest.exceptions;

public class BookBannedException extends RuntimeException {

    public BookBannedException() {
        super();
    }

    public BookBannedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BookBannedException(final String message) {
        super(message);
    }

    public BookBannedException(final Throwable cause) {
        super(cause);
    }
}
