package br.com.investimentos.infra;

import br.com.investimentos.exceptions.AcaoNotFoundException;
import br.com.investimentos.exceptions.CarteiraNotFoundException;
import br.com.investimentos.exceptions.UsuarioInvalidoException;
import br.com.investimentos.exceptions.UsuarioNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsuarioNotFoundException.class)
    private ResponseEntity<RestErrorMessage> usuarioNotFound(UsuarioNotFoundException ex){
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(UsuarioInvalidoException.class)
    private ResponseEntity<RestErrorMessage> usuarioInvalido(UsuarioInvalidoException ex){
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(CarteiraNotFoundException.class)
    private ResponseEntity<RestErrorMessage> carteiraNotFound(CarteiraNotFoundException ex){
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(AcaoNotFoundException.class)
    private ResponseEntity<RestErrorMessage> acaoNotFound(AcaoNotFoundException ex){
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

}