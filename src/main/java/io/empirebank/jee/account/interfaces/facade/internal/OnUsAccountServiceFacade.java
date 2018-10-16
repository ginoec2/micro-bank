package io.empirebank.jee.account.interfaces.facade.internal;

import io.empirebank.jee.account.domain.model.Account;
import io.empirebank.jee.account.application.AccountService;
import io.empirebank.jee.account.interfaces.facade.AccountServiceFacade;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collection;

@Stateless
public class OnUsAccountServiceFacade implements AccountServiceFacade {

    @Inject
    private AccountService accountService;

    @Override
    public Account findAccountById(String accountNumber) {

        return accountService.findById(accountNumber);
    }

    @Override
    public Collection<Account> findAllAccounts() {

        return accountService.findAll();
    }
}
