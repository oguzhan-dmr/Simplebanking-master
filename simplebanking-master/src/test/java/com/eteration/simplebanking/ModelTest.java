package com.eteration.simplebanking;



import static org.junit.jupiter.api.Assertions.assertTrue;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.WithdrawalTransaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ModelTest {

//
//	@Test
//	public void testCreateAccountAndSetBalance0() {
//		Account account = new Account("Kerem Karaca", "17892");
//		assertTrue(account.getOwner().equals("Kerem Karaca"));
//		assertTrue(account.getAccountNumber().equals("17892"));
//		assertTrue(account.getBalance() == new BigDecimal(0));
//	}
//
//	@Test
//	public void testDepositIntoBankAccount() {
//		Account account = new Account("Demet Demircan", "9834");
//		account.deposit(BigDecimal.valueOf(100));
//		assertTrue(account.getBalance() == new BigDecimal(100));
//	}
//
//	@Test
//	public void testWithdrawFromBankAccount() throws InsufficientBalanceException {
//		Account account = new Account("Demet Demircan", "9834");
//		account.deposit(100);
//		assertTrue(account.getBalance() == new BigDecimal(100));
//		account.withdraw(50);
//		assertTrue(account.getBalance() == new BigDecimal(50));
//	}
//
//	@Test
//	public void testWithdrawException() {
//		Assertions.assertThrows( InsufficientBalanceException.class, () -> {
//			Account account = new Account("Demet Demircan", "9834");
//			account.deposit(100);
//			account.withdraw(500);
//		  });
//
//	}
//
//	@Test
//	public void testTransactions() {
//		// Create account
//		Account account = new Account("Canan Kaya", "1234");
//		assertTrue(account.getTransactions().size() == 0);
//
//		// Deposit Transaction
//		DepositTransaction depositTrx = new DepositTransaction(new BigDecimal(100));
//		assertTrue(depositTrx.getDate() != null);
//		account.post(depositTrx);
//		assertTrue(account.getBalance() == new BigDecimal(100));
//		assertTrue(account.getTransactions().size() == 1);
//
//		// Withdrawal Transaction
//		WithdrawalTransaction withdrawalTrx = new WithdrawalTransaction(new BigDecimal(60));
//		assertTrue(withdrawalTrx.getDate() != null);
//		//account.post(withdrawalTrx);
//		assertTrue(account.getBalance() == new BigDecimal(40));
//		assertTrue(account.getTransactions().size() == 2);
//	}

}
