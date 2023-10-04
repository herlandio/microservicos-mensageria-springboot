package br.com.herlandio7cardservice.application;

import java.math.BigDecimal;

import br.com.herlandio7cardservice.domain.Card;
import br.com.herlandio7cardservice.domain.CardFlag;
import lombok.Data;

@Data
public class CardSaveRequest {
    private String name;
    private CardFlag flag;
    private BigDecimal income;
    private BigDecimal basicLimit;

    public Card toModel() {
        return new Card(name, flag, income, basicLimit);
    }
}
