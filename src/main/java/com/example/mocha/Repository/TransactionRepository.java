package com.example.mocha.Repository;

import com.example.mocha.model.Transaction;
import com.example.mocha.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findBySenderWallet(Wallet wallet);
}
