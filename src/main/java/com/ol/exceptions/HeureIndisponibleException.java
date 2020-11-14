package com.ol.exceptions;

public class HeureIndisponibleException extends Exception{
	public HeureIndisponibleException() {
		super("le jour sélectionnée ne contient aucune heure");
    }

    public HeureIndisponibleException(String message) {
        super(message);
    }

    public HeureIndisponibleException(String message, Throwable cause) {
        super(message, cause);
    }

    public HeureIndisponibleException(Throwable cause) {
        super(cause);
    }

    public HeureIndisponibleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
