package com.marllonmendez.voll.dto;

import org.springframework.validation.FieldError;

public record DadosErroValidacaoDTO(String campo, String mensagem) {

    public DadosErroValidacaoDTO(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }

}
