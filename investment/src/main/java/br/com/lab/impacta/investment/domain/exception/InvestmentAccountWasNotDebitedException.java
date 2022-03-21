package br.com.lab.impacta.investment.domain.exception;

public class InvestmentAccountWasNotDebitedException extends RuntimeException {
    private String description;

    public String getDescription() {
        return description;
    }

    public InvestmentAccountWasNotDebitedException() { super();}

    public InvestmentAccountWasNotDebitedException(String message, String description) {
        super(message);
        this.description = description;
    }
}
