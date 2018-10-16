package io.empirebank.jee.account.interfaces.facade;

import io.empirebank.jee.account.domain.model.Account;

import java.util.Collection;

public interface AccountServiceFacade {

    Account findAccountById(String accountNumber);
    Collection<Account> findAllAccounts();

}
