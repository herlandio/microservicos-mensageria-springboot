package br.com.herlandio7creditassessorservice.infra.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.herlandio7creditassessorservice.domain.model.DataClient;

//Faz a requisição internamente no microserviço de clients atraves do name
@FeignClient(value = "herlandio7-client-service", path = "/clients")
public interface ClientResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<DataClient> dataClient(@RequestParam("cpf") String cpf);
}
