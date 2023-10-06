package br.com.herlandio7cardservice.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CardIssuanceRequest {
    private Long id;
    private String cpf;
    private String address;
    private BigDecimal limitReleased;
}
