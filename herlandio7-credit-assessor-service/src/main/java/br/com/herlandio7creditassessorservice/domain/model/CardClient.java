package br.com.herlandio7creditassessorservice.domain.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CardClient {
    private String name;
    private String flag;
    private BigDecimal limitReleased;
}
