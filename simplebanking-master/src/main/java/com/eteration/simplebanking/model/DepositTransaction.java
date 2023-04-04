package com.eteration.simplebanking.model;


import java.math.BigDecimal;
public class DepositTransaction extends Transaction {
    public DepositTransaction(){
}
    public DepositTransaction(BigDecimal amount) {
        super(amount);
    }
}
