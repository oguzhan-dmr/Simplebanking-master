package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class BillPaymentController {
    @Autowired
    AccountService accountService;

    @PutMapping("/{accountNumber}/bill")
    public ResponseEntity<TransactionStatus> saveBill(@PathVariable String accountNumber,@RequestBody BillPaymentTransaction billPaymentTransaction) throws InsufficientBalanceException, AccountNotFoundException {
        Account account = accountService.findAccount(accountNumber);
        if (account==null)
        {
            throw new AccountNotFoundException("Account Not Found !");
        }
        accountService.bill(account,billPaymentTransaction);
        return new ResponseEntity<>(new TransactionStatus("OK"), HttpStatus.OK);
    }
}
