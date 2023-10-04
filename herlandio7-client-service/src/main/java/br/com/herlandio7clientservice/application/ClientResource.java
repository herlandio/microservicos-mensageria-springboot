package br.com.herlandio7clientservice.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clients") // endpoint
public class ClientResource {

    @GetMapping
    public String status() {
        return "ok";
    }
}
