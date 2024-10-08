package br.com.investimentos.exceptions;

public class AcaoInfoException extends RuntimeException {
    public AcaoInfoException(String message) {
        super(message);
    }

    public AcaoInfoException(String message, Throwable cause) {
        super(message, cause);
    }
}