package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    private Account account;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setUp() {
        account = new Account();
        account.setAccountNumber("123456");
        account.setOwner("Oguzhan Demir");
        account.setBalance(new BigDecimal(1000));

    }

    @Test
    public void testSaveAccount() throws Exception {
        when(accountService.saveAccount(account)).thenReturn(account);

        ResponseEntity<Account> response = accountController.saveAccount(account);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(response.getBody().getAccountNumber(), "123456");
        assertEquals(response.getBody().getOwner(), "Oguzhan Demir");
        assertEquals(response.getBody().getBalance(), new BigDecimal(1000));
    }

    @Test
    public void testGetAccount() throws Exception {
        when(accountService.findAccount(any(String.class))).thenReturn(account);

        mockMvc.perform(get("/account/123456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber").value("123456"))
                .andExpect(jsonPath("$.balance").value(1000));
    }


    @Test
    public void debit_success() throws AccountNotFoundException, InsufficientBalanceException {

        WithdrawalTransaction withdrawalTransaction = new WithdrawalTransaction(new BigDecimal("200"));


        when(accountService.findAccount("1234567890")).thenReturn(account);
        accountController.debit("1234567890", withdrawalTransaction);

        ResponseEntity<TransactionStatus> response = new ResponseEntity<>(new TransactionStatus("OK"), HttpStatus.OK);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK", response.getBody().getStatus());
    }

    @Test
    public void debit_accountNotFound() throws AccountNotFoundException, InsufficientBalanceException {

        WithdrawalTransaction withdrawalTransaction = new WithdrawalTransaction(new BigDecimal("200"));


        when(accountService.findAccount("1234567890")).thenReturn(null);

        try {
            accountController.debit("1234567890", withdrawalTransaction);
        } catch (AccountNotFoundException e) {
            assertEquals(AccountNotFoundException.class, e.getClass());
        }
    }


    @Test
    public void credit_success() throws AccountNotFoundException {

        //Given
        var depositTransaction = new DepositTransaction(new BigDecimal("200"));

        when(accountService.findAccount("1234567890")).thenReturn(account);
        //When
        accountController.credit("1234567890", depositTransaction);

        ResponseEntity<TransactionStatus> response = new ResponseEntity<>(new TransactionStatus("OK"), HttpStatus.OK);
        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK", response.getBody().getStatus());
    }

    @Test
    public void credit_accountNotFound() throws AccountNotFoundException {
        var depositTransaction = new DepositTransaction(new BigDecimal("200"));

        when(accountService.findAccount("1234567890")).thenReturn(null);

        try {
            accountController.credit("1234567890", depositTransaction);
        } catch (AccountNotFoundException e) {
            assertEquals(AccountNotFoundException.class, e.getClass());
        }
    }

}
