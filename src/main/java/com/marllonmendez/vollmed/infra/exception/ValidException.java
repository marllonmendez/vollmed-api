package com.marllonmendez.vollmed.infra.exception;

public class ValidException extends RuntimeException {
    public ValidException(String mensagem) {
        super(mensagem);
    }
}
