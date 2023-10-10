package com.picpaysimplificado.domain.transaction;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dtos.TransactionForm;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;
    private LocalDateTime timeStamp;

    public Transaction(TransactionForm transactionForm, User sender, User receiver) {
        this.amount = transactionForm.value();
        this.sender = sender;
        this.receiver = receiver;
        this.timeStamp = LocalDateTime.now();
    }

}
