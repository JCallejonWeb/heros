package com.prueba.heros.config;

import org.springframework.http.HttpStatus;

public class HerosException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2965174902693620078L;
	private final HttpStatus httpStatus;
    private final String mensaje;

    public HerosException(HttpStatus httpStatus, String mensaje) {
        this.httpStatus = httpStatus;
        this.mensaje = mensaje;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMensaje() {
        return mensaje;
    }
}
