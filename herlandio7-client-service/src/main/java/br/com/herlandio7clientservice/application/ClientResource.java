package br.com.herlandio7clientservice.application;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.herlandio7clientservice.domain.Client;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("clients") // endpoint
@RequiredArgsConstructor
public class ClientResource {

    private final ClientService service;

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody ClientSaveRequest request) {
        var client = request.toModel();
        service.save(client);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(client.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<Optional<Client>> dataClient(@RequestParam("cpf") String cpf) {
        var client = service.getByCPF(cpf);
        if (client.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }
}
