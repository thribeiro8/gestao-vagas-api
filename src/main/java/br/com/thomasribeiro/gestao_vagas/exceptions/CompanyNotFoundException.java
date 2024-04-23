package br.com.thomasribeiro.gestao_vagas.exceptions;

public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException() {
        super("Company not found");
    }

    public CompanyNotFoundException(String message) {
        super(message);
    }
}
