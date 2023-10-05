package br.com.herlandio7creditassessorservice.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FeedbackCustomerEvaluation {
    private List<ApprovedCard> cards;
}
