package br.com.herlandio7creditassessorservice.infra.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.herlandio7creditassessorservice.domain.model.CardClient;

//Faz a requisição internamente no microserviço de cards atraves do name
@FeignClient(value = "herlandio7-card-service", path = "/cards")
public interface CardResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CardClient>> getCardByClient(@RequestParam("cpf") String cpf);
}
