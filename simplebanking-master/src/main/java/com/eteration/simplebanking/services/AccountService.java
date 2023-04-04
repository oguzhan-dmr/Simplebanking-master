package com.eteration.simplebanking.services;

import com.eteration.simplebanking.Repository.AccountRepository;
import com.eteration.simplebanking.Repository.TransactionRepository;
import com.eteration.simplebanking.model.*;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
@Service
public class AccountService {
    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository=transactionRepository;
    }
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }
    public void debit(Account account, WithdrawalTransaction withdrawalTransaction) throws InsufficientBalanceException {
        if (account.getBalance().compareTo(withdrawalTransaction.getAmount())<0){
            throw new InsufficientBalanceException("Insufficient Balance !");
        }
        BigDecimal accountNewBalance=account.getBalance().subtract(withdrawalTransaction.getAmount());
        account.setBalance(accountNewBalance);
        accountRepository.save(account);
        Transaction transaction=new Transaction(withdrawalTransaction.getAmount(),TransactionType.DEBIT);
        transactionRepository.save(transaction);
    }
    public void credit(Account account, DepositTransaction depositTransaction) {
        BigDecimal acoountNewBalance = account.getBalance().add(depositTransaction.getAmount());
        account.setBalance(acoountNewBalance);
        accountRepository.save(account);
        Transaction transaction = new Transaction(depositTransaction.getAmount(),TransactionType.CREDIT);
        transactionRepository.save(transaction);
    }
    public void bill(Account account,BillPaymentTransaction billPaymentTransaction) throws InsufficientBalanceException {
        if (account.getBalance().compareTo(billPaymentTransaction.getAmount())<0){
            throw new InsufficientBalanceException("Insufficient Balance !");
        }
        BigDecimal accountNewBalance=account.getBalance().subtract(billPaymentTransaction.getAmount());
        account.setBalance(accountNewBalance);
        Transaction transaction=new Transaction(billPaymentTransaction.getAmount(),billPaymentTransaction.getPhoneNumber(),billPaymentTransaction.getPayee(),TransactionType.BILL);
        transactionRepository.save(transaction);
    }
    public Account findAccount(String accountNumber) throws AccountNotFoundException {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException("Account Not Found !");
        }
        return account;
    }

}


