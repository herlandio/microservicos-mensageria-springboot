package br.com.herlandio7creditassessorservice.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.herlandio7creditassessorservice.application.exception.DataClientNotFoundException;
import br.com.herlandio7creditassessorservice.application.exception.ErrorComunicationMicroservicesException;
import br.com.herlandio7creditassessorservice.domain.model.CustomerSituation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("credit-ratings")
@RequiredArgsConstructor
public class CreditAssessorController {

    private final CreditServiceAppraiser creditService;

    @GetMapping(value = "customer-situation", name = "cpf")
    public ResponseEntity checkCustomerSituation(@RequestParam("cpf") String cpf) {
        try {
            CustomerSituation customerSituation = creditService.getCustomerSituation(cpf);
            return ResponseEntity.ok(customerSituation);
        } catch (DataClientNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorComunicationMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
}
