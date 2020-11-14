package com.ol.exceptions;

public class HeureDeCoursIndisponibleException extends Exception{
	public HeureDeCoursIndisponibleException() {
		super("l'heure de cours sélectionnée est indisponible");
    }

    public HeureDeCoursIndisponibleException(String message) {
        super(message);
    }

    public HeureDeCoursIndisponibleException(String message, Throwable cause) {
        super(message, cause);
    }

    public HeureDeCoursIndisponibleException(Throwable cause) {
        super(cause);
    }

    public HeureDeCoursIndisponibleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
