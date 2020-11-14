package com.ol.exceptions;

public class HeureConsultationIndisponibleException extends Exception{
	public HeureConsultationIndisponibleException() {
		super("Aucune heure de rdv disponible ce jour là, choisissez une autre date ou laissez ce champ vide");
    }

    public HeureConsultationIndisponibleException(String message) {
        super(message);
    }

    public HeureConsultationIndisponibleException(String message, Throwable cause) {
        super(message, cause);
    }

    public HeureConsultationIndisponibleException(Throwable cause) {
        super(cause);
    }

    public HeureConsultationIndisponibleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
