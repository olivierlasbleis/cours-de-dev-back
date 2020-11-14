package com.ol.exceptions;

public class ClientIndisponibleException extends Exception{
	public ClientIndisponibleException() {
		super();
    }

    public ClientIndisponibleException(String message) {
        super(message);
    }

    public ClientIndisponibleException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientIndisponibleException(Throwable cause) {
        super(cause);
    }

    public ClientIndisponibleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
