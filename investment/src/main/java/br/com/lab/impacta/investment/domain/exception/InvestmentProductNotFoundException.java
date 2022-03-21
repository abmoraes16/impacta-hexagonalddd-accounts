package br.com.lab.impacta.investment.domain.exception;

import lombok.Data;

public class InvestmentProductNotFoundException extends RuntimeException{
    private String description;

    public String getDescription() {
        return description;
    }

    public InvestmentProductNotFoundException() { super();}

    public InvestmentProductNotFoundException(String message, String description) {
        super(message);
        this.description = description;
    }
}
