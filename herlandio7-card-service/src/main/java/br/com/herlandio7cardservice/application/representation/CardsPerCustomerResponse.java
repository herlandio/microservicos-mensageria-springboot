package br.com.herlandio7cardservice.application.representation;

import java.math.BigDecimal;

import br.com.herlandio7cardservice.domain.CardClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsPerCustomerResponse {
    private String name;
    private String flag;
    private BigDecimal limit;

    public static CardsPerCustomerResponse fromModel(CardClient model) {
        return new CardsPerCustomerResponse(
                model.getCard().getName(),
                model.getCard().getFlag().toString(),
                model.getLimitBasic());
    }
}
