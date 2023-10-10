package com.picpaysimplificado.dtos;

import com.picpaysimplificado.domain.transaction.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO(BigDecimal value, UserDTO sender, UserDTO receiver, LocalDateTime localDateTime) {

    public TransactionDTO(Transaction transaction) {
        this(transaction.getAmount(), new UserDTO(transaction.getSender()), new UserDTO(transaction.getReceiver()), transaction.getTimeStamp());
    }

}
