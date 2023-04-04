package com.eteration.simplebanking.model;


import java.math.BigDecimal;

// This class is a place holder you can change the complete implementation
public class WithdrawalTransaction extends Transaction{
    public WithdrawalTransaction() {

        super();
    }

    public WithdrawalTransaction(BigDecimal amount) {
        super(amount);
    }
}


