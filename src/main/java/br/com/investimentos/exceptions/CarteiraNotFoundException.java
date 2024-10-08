package br.com.investimentos.exceptions;

public class CarteiraNotFoundException extends RuntimeException {

    public CarteiraNotFoundException() {
        super("Carteira não encontrado");
    }

    public CarteiraNotFoundException(String message) {
        super(message);
    }
}
