package io.empirebank.jee.account.interfaces.facade.internal;

import io.empirebank.jee.account.application.AccountService;
import io.empirebank.jee.account.domain.model.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.PersistenceException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OnUsAccountServiceFacadeTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private OnUsAccountServiceFacade onUsAccountServiceFacade = new OnUsAccountServiceFacade();


    @Test
    public void findByIdShouldReturnAccountWhenExists() {
        String accountNumber = "123456";
        Account expectedAccount = new Account();
        expectedAccount.setAccountNumber(accountNumber);
        when(accountService.findById(accountNumber)).thenReturn(expectedAccount);

        Account actualAccount = onUsAccountServiceFacade.findAccountById(accountNumber);

        assertThat(actualAccount).isEqualTo(expectedAccount);
    }

    @Test
    public void findByIdShouldReturnNullWhenNotFound() {
        String accountNumber = "123456";
        Account expectedAccount = null;
        when(accountService.findById(accountNumber)).thenReturn(expectedAccount);

        Account actualAccount = onUsAccountServiceFacade.findAccountById(accountNumber);

        assertThat(actualAccount).isNull();
        assertThat(actualAccount).isEqualTo(expectedAccount);
    }

    @Test(expected = PersistenceException.class)
    public void findByIdShouldThrowAnExpcetionWhenDBException() {
        String accountNumber = "123456";
        when(accountService.findById(accountNumber)).thenThrow(new PersistenceException());

        Account actualAccount = onUsAccountServiceFacade.findAccountById(accountNumber);
    }

    @Test
    public void findAllAccountsShouldReturnEmptyListWhenNoAccounts() {
        List<Account> expectedAccountList = Collections.EMPTY_LIST;
        when(accountService.findAll()).thenReturn(expectedAccountList);

        Collection<Account> actualAccountList = onUsAccountServiceFacade.findAllAccounts();

        assertThat(actualAccountList).isEmpty();
    }

    @Test
    public void findAllAccountsShouldReturnAccountListWhenFoundAccounts() {
        List<Account> expectedAccountList = Collections.singletonList(new Account());
        when(accountService.findAll()).thenReturn(expectedAccountList);

        Collection<Account> actualAccountList = onUsAccountServiceFacade.findAllAccounts();

        assertThat(actualAccountList).isEqualTo(expectedAccountList);
        assertThat(actualAccountList.size()).isEqualTo(1);
    }

    @Test(expected = PersistenceException.class)
    public void findAllAccountsShouldThrowAnExpcetionWhenDBException() {
        when(accountService.findAll()).thenThrow(new PersistenceException());

        Collection<Account> actualAccountList = onUsAccountServiceFacade.findAllAccounts();
    }
}