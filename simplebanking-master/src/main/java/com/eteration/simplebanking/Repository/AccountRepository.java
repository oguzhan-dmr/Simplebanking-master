package com.eteration.simplebanking.Repository;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.BillPaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository <Account, Long>{

    Account findByAccountNumber(String accountNumber);


}
