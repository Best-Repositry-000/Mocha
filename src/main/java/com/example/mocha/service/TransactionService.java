package com.example.mocha.service;

import com.example.mocha.Repository.TransactionRepository;
import com.example.mocha.Repository.UserRepository;
import com.example.mocha.config.SecurityConfig;
import com.example.mocha.dto.TransferDto;
import com.example.mocha.model.Transaction;
import com.example.mocha.model.User;
import com.example.mocha.model.Wallet;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepo;
    private final UserRepository userRepo;

    @Transactional
    public void transfer(TransferDto request) {

        String senderMatricNo = SecurityConfig.getCurrentUserMatricNo();


        User sender = userRepo.findByMatricNo(senderMatricNo).
                orElseThrow(() -> new RuntimeException("User not found"));
        User receiver = userRepo.findByMatricNo(request.getReceiverMatricNo()).
                orElseThrow(() -> new RuntimeException("User not found"));

        Wallet senderWallet = sender.getWallet();
        Wallet receiverWallet = receiver.getWallet();

        BigDecimal amount = request.getAmount();

        if (senderWallet.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        senderWallet.setBalance(senderWallet.getBalance().subtract(amount));
        receiverWallet.setBalance(receiverWallet.getBalance().add(amount));

        Transaction tx = Transaction.transfer(amount, senderWallet, receiverWallet);

        transactionRepo.save(tx);
    }
}
