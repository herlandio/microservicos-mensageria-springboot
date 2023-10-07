package br.com.herlandio7creditassessorservice.application.exception;

public class DataClientNotFoundException extends Exception {
    public DataClientNotFoundException() {
        super("Data of client not found for CPF");
    }
}
