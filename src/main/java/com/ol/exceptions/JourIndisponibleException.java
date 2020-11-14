package com.ol.exceptions;

public class JourIndisponibleException extends Exception {
	public JourIndisponibleException() {
		super("le jourDeCours que vous essayez de créer existe déjà");
    }

    public JourIndisponibleException(String message) {
        super(message);
    }

    public JourIndisponibleException(String message, Throwable cause) {
        super(message, cause);
    }

    public JourIndisponibleException(Throwable cause) {
        super(cause);
    }

    public JourIndisponibleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
