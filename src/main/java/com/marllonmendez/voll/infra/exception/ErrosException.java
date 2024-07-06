package com.marllonmendez.voll.infra.exception;

import com.marllonmendez.voll.dto.DadosErroValidacaoDTO;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrosException {

    // Tratamento de erro para dados não encontrados
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404() {
        return ResponseEntity.notFound().build();
    }

    // Tratamento de erro na validação dos dados
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400(MethodArgumentNotValidException exception) {
        var errors = exception.getBindingResult().getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DadosErroValidacaoDTO::new).toList());
    }

}
