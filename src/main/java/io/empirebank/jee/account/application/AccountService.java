package io.empirebank.jee.account.application;

import io.empirebank.jee.account.domain.model.Account;

import java.math.BigDecimal;
import java.util.Collection;

public interface AccountService {

    Account findById(String accountNumber);
    Collection<Account> findAll();
    Account deposit(String accountNumber, BigDecimal amount) throws Exception;
    Account withdraw(String accountNumber, BigDecimal amount) throws Exception;
}
