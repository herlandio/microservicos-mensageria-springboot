package br.com.herlandio7cardservice.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.herlandio7cardservice.application.representation.CardSaveRequest;
import br.com.herlandio7cardservice.application.representation.CardsPerCustomerResponse;
import br.com.herlandio7cardservice.domain.Card;
import br.com.herlandio7cardservice.domain.CardClient;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
public class CardResource {

    @GetMapping
    public String status() {
        return "ok";
    }

    private final CardService clientService;
    private final CardClientService cardService;

    @PostMapping
    public ResponseEntity<Card> save(@RequestBody CardSaveRequest request) {
        Card card = request.toModel();
        clientService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getIncomeCardUpTo(@RequestParam("income") Long income) {
        List<Card> list = clientService.getLowerIncomeCard(income);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardsPerCustomerResponse>> getCardByClient(@RequestParam("cpf") String cpf) {
        List<CardClient> list = cardService.listCardsByCpf(cpf);
        List<CardsPerCustomerResponse> result = list.stream()
                .map(CardsPerCustomerResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }
}
