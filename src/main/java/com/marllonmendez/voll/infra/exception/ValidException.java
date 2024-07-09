package com.marllonmendez.voll.infra.exception;

public class ValidException extends RuntimeException {
    public ValidException(String mensagem) {
        super(mensagem);
    }
}
