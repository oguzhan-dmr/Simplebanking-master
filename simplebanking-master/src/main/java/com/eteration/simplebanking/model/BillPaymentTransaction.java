package com.eteration.simplebanking.model;

import java.math.BigDecimal;
public class BillPaymentTransaction extends Transaction{

    public BillPaymentTransaction(){
    }
    BillPaymentTransaction(BigDecimal amount, String payee, String phoneNumber){
        super(amount,payee,phoneNumber);
    }
    public BillPaymentTransaction(BigDecimal amount) {
        super(amount);
    }
    public BillPaymentTransaction(BigDecimal amount, TransactionType transactionType) {
        super(amount, transactionType);
    }
    BillPaymentTransaction(BigDecimal amount, String phoneNumber, String payee, TransactionType transactionType){
        super(amount,phoneNumber,payee,transactionType);
    }
}
