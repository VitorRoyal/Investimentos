package br.com.investimentos.exceptions;

public class UsuarioInvalidoException extends RuntimeException {

    public UsuarioInvalidoException() {
        super("Usuário inválido");
    }

    public UsuarioInvalidoException(String message) {
        super(message);
    }
}
