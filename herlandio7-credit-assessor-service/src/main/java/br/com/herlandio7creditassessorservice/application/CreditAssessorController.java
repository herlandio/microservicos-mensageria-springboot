package br.com.herlandio7creditassessorservice.application;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.herlandio7creditassessorservice.domain.model.CustomerSituation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("credit-ratings")
@RequiredArgsConstructor
public class CreditAssessorController {

    private final CreditServiceAppraiser creditService;

    @GetMapping(value = "customer-situation", name = "cpf")
    public ResponseEntity<CustomerSituation> checkCustomerSituation(@RequestParam("cpf") String cpf) {
        CustomerSituation customerSituation = creditService.getCustomerSituation(cpf);
        return ResponseEntity.ok(customerSituation);
    }
}
