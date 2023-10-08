package com.picpaysimplificado.dtos;

import com.picpaysimplificado.domain.transaction.Transaction;

import java.math.BigDecimal;

public record TransactionForm(BigDecimal value, Long senderId, Long receiverId) {
}
