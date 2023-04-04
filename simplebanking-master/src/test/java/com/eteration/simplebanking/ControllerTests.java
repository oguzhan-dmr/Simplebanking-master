package com.eteration.simplebanking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.eteration.simplebanking.controller.AccountController;
import com.eteration.simplebanking.model.TransactionStatus;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.services.AccountService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class ControllerTests  {

    @Spy
    @InjectMocks
    private AccountController controller;
 
    @MockBean
    private AccountService service;

    
    @Test
    void givenId_Credit_thenReturnJson()
    throws Exception {
        
        Account account = new Account("Kerem Karaca", "17892");
        doReturn(account).when(service).findAccount( "17892");
        ResponseEntity<TransactionStatus> result = controller.credit( "17892", new DepositTransaction(new BigDecimal(1000)));
        verify(service, times(1)).findAccount("17892");
        assertEquals("OK", result.getBody().getStatus());
    }

    @Test
    void givenId_CreditAndThenDebit_thenReturnJson() throws Exception {

        Account account = new Account("Kerem Karaca", "17892");

        doReturn(account).when(service).findAccount( "17892");
        ResponseEntity<TransactionStatus> result = controller.credit( "17892", new DepositTransaction(new BigDecimal(1000)));
        ResponseEntity<TransactionStatus> result2 = controller.debit( "17892", new WithdrawalTransaction(new BigDecimal(50)));
        verify(service, times(2)).findAccount("17892");
        assertEquals("OK", result.getBody().getStatus());
        assertEquals("OK", result2.getBody().getStatus());
        assertEquals(BigDecimal.valueOf(950), account.getBalance());
    }


    @Test
    void givenId_CreditAndThenDebitMoreGetException_thenReturnJson()
    throws Exception {
        Assertions.assertThrows( InsufficientBalanceException.class, () -> {
            Account account = new Account("Kerem Karaca", "17892");

            doReturn(account).when(service).findAccount( "17892");
            ResponseEntity<TransactionStatus> result = controller.credit( "17892", new DepositTransaction(new BigDecimal(1000)));
            assertEquals("OK", result.getBody().getStatus());
            assertEquals(BigDecimal.valueOf(1000), account.getBalance());
            verify(service, times(1)).findAccount("17892");

            ResponseEntity<TransactionStatus> result2 = controller.debit( "17892", new WithdrawalTransaction(new BigDecimal(5000)));
        });
    }

    @Test
    void givenId_GetAccount_thenReturnJson()
    throws Exception {
        
        Account account = new Account("Kerem Karaca", "17892");

        doReturn(account).when(service).findAccount( "17892");
        ResponseEntity<Account> result = controller.getAccount( "17892");
        verify(service, times(1)).findAccount("17892");
        assertEquals(account, result.getBody());
    }

}
