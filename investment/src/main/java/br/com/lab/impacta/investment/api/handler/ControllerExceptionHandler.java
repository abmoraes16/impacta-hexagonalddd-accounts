package br.com.lab.impacta.investment.api.handler;

import br.com.lab.impacta.investment.application.dto.response.ErrorMessageResponse;
import br.com.lab.impacta.investment.domain.exception.InvestmentAccountWasNotDebitedException;
import br.com.lab.impacta.investment.domain.exception.InvestmentAccountWithoutBalanceException;
import br.com.lab.impacta.investment.domain.exception.InvestmentAccountWithoutBalanceForProductPrivateException;
import br.com.lab.impacta.investment.domain.exception.InvestmentProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final String MESSAGE_ERROR_DEFAULT = "Não foi possivel processar sua requisição.";

    public ResponseEntity<ErrorMessageResponse> investmentProductNotFound(InvestmentProductNotFoundException exception){
        return getErrorMessageResponse(HttpStatus.NOT_FOUND, exception.getMessage(), exception.getDescription());
    }

    @ExceptionHandler(InvestmentAccountWithoutBalanceException.class)
    public ResponseEntity<ErrorMessageResponse> investmentAccountWithoutBalance(InvestmentAccountWithoutBalanceException exception){
        return getErrorMessageResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), exception.getDescription());
    }

    @ExceptionHandler(InvestmentAccountWithoutBalanceForProductPrivateException.class)
    public ResponseEntity<ErrorMessageResponse> investmentAccountWithoutBalanceForProductPrivate(InvestmentAccountWithoutBalanceForProductPrivateException exception){
        return getErrorMessageResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), exception.getDescription());
    }

    @ExceptionHandler(InvestmentAccountWasNotDebitedException.class)
    public ResponseEntity<ErrorMessageResponse> investmentAccountWasNotDebited(InvestmentAccountWasNotDebitedException exception){
        return getErrorMessageResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), exception.getDescription());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessageResponse> errorGeneral(RuntimeException exception){
        return getErrorMessageResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), MESSAGE_ERROR_DEFAULT);
    }

    private ResponseEntity<ErrorMessageResponse> getErrorMessageResponse(HttpStatus httpStatus, String message, String description) {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(
                httpStatus.value(),
                new Date(),
                message,
                description
        );

        return new ResponseEntity<>(errorMessageResponse, httpStatus);
    }
}
