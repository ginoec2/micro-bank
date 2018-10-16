package io.empirebank.jee.transfer.interfaces.facade.internal;

import io.empirebank.jee.account.application.AccountService;
import io.empirebank.jee.account.domain.model.Account;
import io.empirebank.jee.account.infrastructure.exception.AccountException;
import io.empirebank.jee.transfer.application.TransferService;
import io.empirebank.jee.transfer.domain.model.Transfer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.PersistenceException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountMoneyTransferServiceFacadeTest {

    @Mock
    private TransferService transferService;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountMoneyTransferServiceFacade accountMoneyTransferServiceFacade = new AccountMoneyTransferServiceFacade();


    @Test
    public void findTransferByIdShouldReturnTransferWhenExists() {
        Long transferId = 1L;
        Transfer expectedTransfer =  new Transfer();
        expectedTransfer.setId(transferId);
        when(transferService.findById(transferId)).thenReturn(expectedTransfer);

        Transfer actualTransfer = accountMoneyTransferServiceFacade.findTransferById(transferId);

        assertThat(actualTransfer).isEqualTo(expectedTransfer);
        assertThat(actualTransfer.getId()).isEqualTo(transferId);
    }

    @Test
    public void findTransferByIdShouldReturnNullWhenNotFound() {
        Long transferId = 1L;
        Transfer expectedTransfer =  null;
        when(transferService.findById(transferId)).thenReturn(expectedTransfer);

        Transfer actualTransfer = accountMoneyTransferServiceFacade.findTransferById(transferId);

        assertThat(actualTransfer).isEqualTo(expectedTransfer);
    }

    @Test(expected = PersistenceException.class)
    public void findTransferByIdShouldThrowAnExceptionWhenDBException() {
        Long transferId = 1L;
        Transfer expectedTransfer =  null;
        when(transferService.findById(transferId)).thenThrow(new PersistenceException());

        Transfer actualTransfer = accountMoneyTransferServiceFacade.findTransferById(transferId);
    }

    @Test
    public void findAllTransfersShouldReturnEmptyListWhenNoTransfers() {
        List<Transfer> expectedTransferList = Collections.EMPTY_LIST;
        when(transferService.findAll()).thenReturn(expectedTransferList);

        Collection<Transfer> actualTransferList = accountMoneyTransferServiceFacade.findAllTransfers();

        assertThat(actualTransferList).isEmpty();
    }

    @Test
    public void findAllTransfersShouldReturnNonEmptyListWhenFoundTransfers() {
        List<Transfer> expectedTransferList = Collections.singletonList(new Transfer());
        when(transferService.findAll()).thenReturn(expectedTransferList);

        Collection<Transfer> actualTransferList = accountMoneyTransferServiceFacade.findAllTransfers();

        assertThat(actualTransferList).isNotEmpty();
        assertThat(actualTransferList.size()).isEqualTo(1);
    }

    @Test(expected = PersistenceException.class)
    public void findAllTransfersShouldThrowAnExceptionWhenDBException() {
        when(transferService.findAll()).thenThrow(new PersistenceException());

        accountMoneyTransferServiceFacade.findAllTransfers();
    }

    @Test
    public void createTransferShouldReturnTransferWhenAccountsAndBalanceAreValid() throws Exception{
        String fromAccountNumber = "12345";
        String toAccountNumber = "67890";
        BigDecimal amount = new BigDecimal(100);
        Account fromAccount = new Account();
        fromAccount.setAccountNumber(fromAccountNumber);
        fromAccount.setBalance(new BigDecimal(200));
        Account toAccount = new Account();
        fromAccount.setAccountNumber(toAccountNumber);
        fromAccount.setBalance(new BigDecimal(300));
        Transfer expectedTransfer = new Transfer();

        when(accountService.withdraw(fromAccountNumber,amount)).thenReturn(fromAccount);
        when(accountService.deposit(toAccountNumber,amount)).thenReturn(toAccount);
        when(transferService.create(expectedTransfer)).thenReturn(expectedTransfer);

        Account expectedFromAccount = accountService.withdraw(fromAccountNumber,amount);
        Account expectedToAccount = accountService.deposit(toAccountNumber,amount);
        Transfer actualTransfer = transferService.create(expectedTransfer);

        assertThat(actualTransfer).isEqualTo(expectedTransfer);
    }

    @Test(expected = AccountException.class)
    public void createTransferShouldThrowAnExceptionWhenFromAccountNotFoundOrBalanceNotAvailable() throws Exception{
        String fromAccountNumber = "12345";
        String toAccountNumber = "67890";
        BigDecimal amount = new BigDecimal(100);
        Account fromAccount = new Account();
        fromAccount.setAccountNumber(fromAccountNumber);
        fromAccount.setBalance(new BigDecimal(200));
        Account toAccount = new Account();
        toAccount.setAccountNumber(toAccountNumber);
        toAccount.setBalance(new BigDecimal(300));
        Transfer expectedTransfer = new Transfer();

        when(accountService.withdraw(fromAccountNumber,amount)).thenThrow(new AccountException(""));

        when(accountService.deposit(toAccountNumber,amount)).thenReturn(toAccount);
        when(transferService.create(expectedTransfer)).thenReturn(expectedTransfer);

        Account expectedFromAccount = accountService.withdraw(fromAccountNumber,amount);
        Account expectedToAccount = accountService.deposit(toAccountNumber,amount);
        Transfer actualTransfer = transferService.create(expectedTransfer);

        assertThat(actualTransfer).isEqualTo(expectedTransfer);
    }

    @Test(expected = AccountException.class)
    public void createTransferShouldThrowAnExceptionWhenToAccountNotFound() throws Exception{
        String fromAccountNumber = "12345";
        String toAccountNumber = "67890";
        BigDecimal amount = new BigDecimal(100);
        Account fromAccount = new Account();
        fromAccount.setAccountNumber(fromAccountNumber);
        fromAccount.setBalance(new BigDecimal(200));
        Transfer expectedTransfer = new Transfer();

        when(accountService.withdraw(fromAccountNumber,amount)).thenReturn(fromAccount);
        when(accountService.deposit(toAccountNumber,amount)).thenThrow(new AccountException(""));
        when(transferService.create(expectedTransfer)).thenReturn(expectedTransfer);

        Account expectedFromAccount = accountService.withdraw(fromAccountNumber,amount);
        Account expectedToAccount = accountService.deposit(toAccountNumber,amount);
        Transfer actualTransfer = transferService.create(expectedTransfer);

        assertThat(actualTransfer).isEqualTo(expectedTransfer);
    }

}