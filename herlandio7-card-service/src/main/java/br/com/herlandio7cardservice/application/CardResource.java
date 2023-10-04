package br.com.herlandio7cardservice.application;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.herlandio7cardservice.application.representation.CardSaveRequest;
import br.com.herlandio7cardservice.domain.Card;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
public class CardResource {

    @GetMapping
    public String status() {
        return "ok";
    }

    private final CardService service;

    @PostMapping
    public ResponseEntity<Card> save(@RequestBody CardSaveRequest request) {
        Card card = request.toModel();
        service.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getIncomeCardUpTo(@RequestParam("income") Long income) {
        List<Card> list = service.getLowerIncomeCard(income);
        return ResponseEntity.ok(list);
    }
}
