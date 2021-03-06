package com.blueharvest.transactionservice.repository;

import com.blueharvest.transactionservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findTopByOrderByIdDesc();
    Optional<Transaction> findByAccountId(long id);
    Optional<List<Transaction>> findAllByAccountId(long id);
}
