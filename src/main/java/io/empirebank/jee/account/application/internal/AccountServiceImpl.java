package io.empirebank.jee.account.application.internal;

import io.empirebank.jee.account.application.AccountService;
import io.empirebank.jee.account.domain.model.Account;
import io.empirebank.jee.account.infrastructure.exception.AccountException;
import io.empirebank.jee.account.domain.model.AccountRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

@Stateless
public class AccountServiceImpl implements AccountService {

    @Inject
    private AccountRepository accountRepository;

    @Override
    public Account findById(String accountNumber) {

        return accountRepository.findBy(accountNumber);
    }

    @Override
    public Collection<Account> findAll() {

        return accountRepository.findAll();
    }

    @Override
    public Account deposit(String accountNumber, BigDecimal amount) throws Exception{
        Account account = Optional.ofNullable(accountRepository.findBy(accountNumber)).orElseThrow(() -> new AccountException("Account not found for deposit"));
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
        //TODO create Transaction
        return account;
    }

    @Override
    public Account withdraw(String accountNumber, BigDecimal amount) throws Exception  {
        Account account = Optional.ofNullable(accountRepository.findBy(accountNumber)).orElseThrow(() -> new AccountException("Account not found for withdrawal"));
        if ( account.getBalance().compareTo(amount) < 0 ) {
            throw new AccountException("Account has no available balance for withdrawal");
        }
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
        //TODO create Transaction
        return account;
    }
}
