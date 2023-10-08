package com.picpaysimplificado.controllers;

import com.picpaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.dtos.TransactionForm;
import com.picpaysimplificado.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionForm transactionForm) throws Exception {
        TransactionDTO transaction = this.transactionService.createTransaction(transactionForm);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> transactionFormList = this.transactionService.getAllTransactions();
        return ResponseEntity.ok(transactionFormList);
    }

}
