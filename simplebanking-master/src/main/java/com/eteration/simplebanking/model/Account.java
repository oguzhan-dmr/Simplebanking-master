package com.eteration.simplebanking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    private String owner;
    private String accountNumber;
    private BigDecimal balance = BigDecimal.ZERO;
    @OneToMany
    private List<Transaction> transactions = new ArrayList<>();
    public Account(){

    }
    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public List<Transaction> getTransactions(){
        return this.transactions;
    }
    public BigDecimal getBalance() {
        return balance;
    }

}
