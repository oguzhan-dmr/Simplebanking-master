package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/add")
        public ResponseEntity<Account> saveAccount(@RequestBody Account account) {
        Account savedAccount = accountService.saveAccount(account);
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }
    @GetMapping("/{accountNumber}")
        public ResponseEntity<Account>getAccount(@PathVariable(name = "accountNumber") String accountNumber) throws AccountNotFoundException {
            Account account = accountService.findAccount(accountNumber);
            if (account==null)
            {
            throw new AccountNotFoundException("Account Not Found !");
            }
            accountService.findAccount(accountNumber);
            return new ResponseEntity<>(account, HttpStatus.OK);
    }
    @PutMapping("/{accountNumber}/debit")
        public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber , @RequestBody WithdrawalTransaction withdrawalTransaction) throws AccountNotFoundException, InsufficientBalanceException {
        Account account = accountService.findAccount(accountNumber);
        if (account==null)
        {
            throw new AccountNotFoundException("Account Not Found !");
        }
        accountService.debit(account,withdrawalTransaction);
        return new ResponseEntity<>(new TransactionStatus("OK"), HttpStatus.OK);
    }
    @PutMapping("/{accountNumber}/credit")
        public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber, @RequestBody DepositTransaction depositTransaction) throws AccountNotFoundException{
        Account account = accountService.findAccount(accountNumber);
        if (account==null)
        {
            throw new AccountNotFoundException("Account Not Found !");
        }
        accountService.credit(account, depositTransaction);
        return new ResponseEntity<>(new TransactionStatus("OK"), HttpStatus.OK);
    }
}