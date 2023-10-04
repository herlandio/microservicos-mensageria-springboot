package br.com.herlandio7clientservice.application.representation;

import br.com.herlandio7clientservice.domain.Client;
import lombok.Data;

@Data // ja realiza os get and sets
public class ClientSaveRequest {
    private String cpf;
    private String name;
    private Integer age;

    public Client toModel() {
        return new Client(cpf, name, age);
    }
}
