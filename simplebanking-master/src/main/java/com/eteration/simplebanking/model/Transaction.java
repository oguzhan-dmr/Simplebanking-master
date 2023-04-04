package com.eteration.simplebanking.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    private Date date = new Date();
    private BigDecimal amount;
    private String payee;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    public Transaction() {

    }
    public Transaction(BigDecimal amount) {
        this.amount = amount;
    }
    public Transaction(BigDecimal amount, TransactionType transactionType) {
        this.amount = amount;
        this.transactionType = transactionType;
    }
    public Transaction(BigDecimal amount, String phoneNumber, String payee, TransactionType transactionType) {
        this.amount=amount;
        this.phoneNumber=phoneNumber;
        this.payee=payee;
        this.transactionType=transactionType;
    }
    public void TransactionType(TransactionType transactionType){
        this.transactionType=transactionType;
        // 1-Credit 2-Debit 3-Bill //
    }
    public Transaction(BigDecimal amount, String payee, String phoneNumber) {
        this.amount = amount;
        this.payee = payee;
        this.phoneNumber = phoneNumber;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getPayee() {
        return payee;
    }
    public void setPayee(String payee) {
        this.payee = payee;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public TransactionType getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
