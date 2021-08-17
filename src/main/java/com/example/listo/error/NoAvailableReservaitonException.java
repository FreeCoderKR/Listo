package com.example.listo.error;

public class NoAvailableReservaitonException extends RuntimeException{
    public NoAvailableReservaitonException() {
        super();
    }

    public NoAvailableReservaitonException(String message) {
        super(message);
    }

    public NoAvailableReservaitonException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAvailableReservaitonException(Throwable cause) {
        super(cause);
    }

    protected NoAvailableReservaitonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
