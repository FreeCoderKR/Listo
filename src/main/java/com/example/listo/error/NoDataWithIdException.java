package com.example.listo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="No such data for the request")
public class NoDataWithIdException extends RuntimeException{
    public NoDataWithIdException() {
        super();
    }

    public NoDataWithIdException(String message) {
        super(message);
    }

    public NoDataWithIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoDataWithIdException(Throwable cause) {
        super(cause);
    }

    protected NoDataWithIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
