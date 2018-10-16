package io.empirebank.jee.account.application.internal;

import io.empirebank.jee.account.domain.model.Account;
import io.empirebank.jee.account.domain.model.AccountRepository;
import io.empirebank.jee.account.infrastructure.exception.AccountException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {


    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService = new AccountServiceImpl();

    @Test
    public void findAllShouldReturnEmptyListWhenNotFoundAccounts() {
        List<Account> accountList = Collections.EMPTY_LIST;
        when(accountRepository.findAll()).thenReturn(accountList);

        Collection<Account> actualList = accountService.findAll();
        verify(accountRepository).findAll();
        assertThat(actualList).isEmpty();
    }

    @Test
    public void findAllShouldReturnNonEmptyListWhenFoundAccounts() {
        List<Account> accountList = Collections.singletonList(new Account());
        when(accountRepository.findAll()).thenReturn(accountList);

        Collection<Account> actualList = accountService.findAll();
        assertThat(actualList.size()).isEqualTo(1);
    }

    @Test
    public void findByAccountNumberShouldReturnAccountWhenExists() {
        String accountNumber = "12345";
        Account expectedAccount = new Account();
        expectedAccount.setAccountNumber(accountNumber);
        when(accountRepository.findBy(accountNumber)).thenReturn(expectedAccount);

        Account actualAccount = accountService.findById(accountNumber);

        assertThat(actualAccount.getAccountNumber()).isEqualTo(expectedAccount.getAccountNumber());
    }

    @Test
    public void findByIdShouldReturnNullWhenTransferNotFound() {
        String accountNumber = "987654";
        Account expectedAccount = null;
        when(accountRepository.findBy(accountNumber)).thenReturn(expectedAccount);

        Account actualAccount = accountService.findById(accountNumber);

        assertThat(actualAccount).isNull();
    }

    @Test
    public void depositShouldSaveAccountAndIncrementBalanceTo200WhenBalance100() throws Exception{
        String accountNumber = "876543";
        BigDecimal amount = new BigDecimal(100);
        Account expectedAccount = new Account();
        expectedAccount.setAccountNumber(accountNumber);
        expectedAccount.setBalance(amount);
        when(accountRepository.findBy(accountNumber)).thenReturn(expectedAccount);
        when(accountRepository.save(expectedAccount)).thenReturn(expectedAccount);

        Account actualAccount = accountService.deposit(accountNumber,amount);

        assertThat(actualAccount.getBalance()).isEqualTo(new BigDecimal(200));
    }

    @Test(expected = AccountException.class)
    public void depositShouldThrowAnExceptionWhenAccountNotFound() throws Exception{
        String accountNumber = "876543";
        BigDecimal amount = new BigDecimal(100);
        Account expectedAccount = null;
        when(accountRepository.findBy(accountNumber)).thenReturn(expectedAccount);

        Account actualAccount = accountService.deposit(accountNumber,amount);
    }

    @Test
    public void withdrawShouldSaveAccountAndDecreaseBalanceToZeroWhenBalance100() throws Exception{
        String accountNumber = "876543";
        BigDecimal amount = new BigDecimal(100);
        Account expectedAccount = new Account();
        expectedAccount.setAccountNumber(accountNumber);
        expectedAccount.setBalance(amount);
        when(accountRepository.findBy(accountNumber)).thenReturn(expectedAccount);
        when(accountRepository.save(expectedAccount)).thenReturn(expectedAccount);

        Account actualAccount = accountService.withdraw(accountNumber,amount);

        assertThat(actualAccount.getBalance()).isEqualTo(new BigDecimal(0));
    }

    @Test(expected = AccountException.class)
    public void withdrawShouldThrowAnExceptionWhenAccountNotFound() throws Exception{
        String accountNumber = "876543";
        BigDecimal amount = new BigDecimal(10);
        Account expectedAccount = null;
        when(accountRepository.findBy(accountNumber)).thenReturn(expectedAccount);

        Account actualAccount = accountService.withdraw(accountNumber,amount);
    }

    @Test(expected = AccountException.class)
    public void withdrawShouldThrowAnExceptionWhenBalanceLessThanAmountToWithdraw() throws Exception{
        String accountNumber = "876543";
        BigDecimal amount = new BigDecimal(10);
        Account expectedAccount = new Account();
        expectedAccount.setAccountNumber(accountNumber);
        expectedAccount.setBalance(new BigDecimal(9));
        when(accountRepository.findBy(accountNumber)).thenReturn(expectedAccount);

        Account actualAccount = accountService.withdraw(accountNumber,amount);
    }


}