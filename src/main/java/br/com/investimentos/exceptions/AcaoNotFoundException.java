package br.com.investimentos.exceptions;

public class AcaoNotFoundException extends RuntimeException {

    public AcaoNotFoundException() {
        super("Ação não encontrado");
    }

    public AcaoNotFoundException(String message) {
        super(message);
    }
}
