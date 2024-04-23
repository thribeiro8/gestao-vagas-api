package br.com.thomasribeiro.gestao_vagas.exceptions;

public class UserFoundException extends RuntimeException {

    public UserFoundException() {
        super("User already exists");
    }

    public UserFoundException(String message) {
        super(message);
    }
}
