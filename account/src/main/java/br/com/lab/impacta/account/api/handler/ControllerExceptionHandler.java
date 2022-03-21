package br.com.lab.impacta.account.api.handler;

import br.com.lab.impacta.account.application.dto.response.ErrorMessageResponse;
import br.com.lab.impacta.account.domain.exception.AccountNotFoundException;
import br.com.lab.impacta.account.domain.exception.AccountWithoutBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Date;

@ControllerAdvice
//interceptar as exceptions da app
public class ControllerExceptionHandler {

    private final String MESSAGE_ERROR_DEFAULT = "Não foi possivel processar sua requisição.";

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> accountNotFoundException(AccountNotFoundException exception){
        return getErrorMessageResponse(HttpStatus.NOT_FOUND, exception.getMessage(), exception.getDescription());
    }

    @ExceptionHandler(AccountWithoutBalanceException.class)
    public ResponseEntity<ErrorMessageResponse> accountWithoutBalanceException(AccountWithoutBalanceException exception){
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
