package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.dtos.TransactionForm;
import com.picpaysimplificado.repositories.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final RestTemplate restTemplate;
    private final NotificationService notificationService;

    public TransactionService(TransactionRepository transactionRepository, UserService userService, RestTemplate restTemplate,
                              NotificationService notificationService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.restTemplate = restTemplate;
        this.notificationService = notificationService;
    }

    public TransactionDTO createTransaction(TransactionForm transactionForm) throws Exception {
        User sender = userService.findUserById(transactionForm.senderId());
        User receiver = userService.findUserById(transactionForm.receiverId());
        userService.validateTransaction(sender, transactionForm.value());

/*        boolean isAuthorized = this.authorizeTransaction(sender, transactionDTO.value());
        if(!isAuthorized) {
            throw new Exception("Transação não autorizada");
        }*/

        Transaction transaction = new Transaction(transactionForm, sender, receiver);
        sender.setBalance(sender.getBalance().subtract(transactionForm.value()));
        receiver.setBalance(receiver.getBalance().add(transactionForm.value()));

        this.transactionRepository.save(transaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Transação enviada com sucesso");
        this.notificationService.sendNotification(receiver, "Transação recebida com sucesso");

        return new TransactionDTO(transaction);
    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6",
            Map.class);

        if(authorizationResponse.getStatusCode() == HttpStatus.OK) {
            String message = (String) authorizationResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else {
            return false;
        }
    }

    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll().stream().map(TransactionDTO::new).toList();
    }
}
