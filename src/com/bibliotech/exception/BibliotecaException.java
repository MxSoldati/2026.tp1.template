package com.bibliotech.exception;

/**
 * Excepcion base para errores de negocio en la biblioteca.
 */
public class BibliotecaException extends Exception {
    public BibliotecaException(String message) {
        super(message);
    }

    public BibliotecaException(String message, Throwable cause) {
        super(message, cause);
    }
}

