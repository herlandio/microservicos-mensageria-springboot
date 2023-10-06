package br.com.herlandio7creditassessorservice.application.exception;

public class ErrorRequestCard extends RuntimeException {
    public ErrorRequestCard(String message) {
        super(message);
    }
}
