package br.com.herlandio7creditassessorservice.application.exception;

import lombok.Getter;

@Getter
public class ErrorComunicationMicroservicesException extends Exception {

    private Integer status;

    public ErrorComunicationMicroservicesException(String message, Integer status) {
        super(message);
        this.status = status;
    }
}
