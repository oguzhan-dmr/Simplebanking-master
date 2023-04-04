package com.eteration.simplebanking.Repository;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {}
