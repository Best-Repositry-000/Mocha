package com.example.mocha.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID transactionId;

    private BigDecimal amount;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "receiver_wallet_id", nullable = false)
    private Wallet receiverWallet;

    @ManyToOne
    @JoinColumn(name = "sender_wallet_id", nullable = false)
    private Wallet senderWallet;

    public static Transaction transfer(
            BigDecimal amount,
            Wallet senderWallet,
            Wallet receiverWallet
    ) {
        Transaction tx = new Transaction();
        tx.amount = amount;
        tx.senderWallet = senderWallet;
        tx.receiverWallet = receiverWallet;
        tx.createdAt = LocalDateTime.now();
        return tx;
    }
}
