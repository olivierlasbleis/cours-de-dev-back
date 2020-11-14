package com.ol.exceptions;

public class JourDeCoursDejaPresentException extends Exception{

	public JourDeCoursDejaPresentException() {
		super("le jourDeCours que vous essayez de créer existe déjà");
    }

    public JourDeCoursDejaPresentException(String message) {
        super(message);
    }

    public JourDeCoursDejaPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public JourDeCoursDejaPresentException(Throwable cause) {
        super(cause);
    }

    public JourDeCoursDejaPresentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
