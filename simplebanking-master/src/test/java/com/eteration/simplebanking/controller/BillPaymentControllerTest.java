package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BillPaymentController.class)
public class BillPaymentControllerTest {

    @InjectMocks
    private BillPaymentController accountController;
    @MockBean
    private AccountService accountService;
    private Account account;
    private BillPaymentTransaction billPaymentTransaction;

    @BeforeEach
    public void setUp() {
        account = new Account("Oguzhan Demir", "1234567890");
        billPaymentTransaction = new BillPaymentTransaction(new BigDecimal("200"));
    }

    @Test
    public void saveBill_success() throws AccountNotFoundException, InsufficientBalanceException {
        when(accountService.findAccount("1234567890")).thenReturn(account);
        accountController.saveBill("1234567890", billPaymentTransaction);

        ResponseEntity<TransactionStatus> response = new ResponseEntity<>(new TransactionStatus("OK"), HttpStatus.OK);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK", response.getBody().getStatus());
    }

    @Test
    public void saveBill_accountNotFound() throws AccountNotFoundException, InsufficientBalanceException {
        when(accountService.findAccount("1234567890")).thenReturn(null);

        try {
            accountController.saveBill("1234567890", billPaymentTransaction);
        } catch (AccountNotFoundException e) {
            assertEquals(AccountNotFoundException.class, e.getClass());
        }
    }


    @Test
    public void saveBill_ifnsufficientBalance() throws AccountNotFoundException, InsufficientBalanceException {
        billPaymentTransaction.setAmount(new BigDecimal("2000"));
        when(accountService.findAccount("1234567890")).thenReturn(account);
        doThrow(InsufficientBalanceException.class).when(accountService).bill(account,billPaymentTransaction);

        assertThrows(InsufficientBalanceException.class, () -> accountController.saveBill("1234567890", billPaymentTransaction));
    }
}


