package io.empirebank.jee.transfer.interfaces.facade.internal;

import io.empirebank.jee.account.application.AccountService;
import io.empirebank.jee.transfer.domain.model.Transfer;
import io.empirebank.jee.transfer.application.TransferService;
import io.empirebank.jee.transfer.interfaces.facade.MoneyTransferServiceFacade;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collection;

@Stateless
public class AccountMoneyTransferServiceFacade implements MoneyTransferServiceFacade {

    @Inject
    private TransferService transferService;
    @Inject
    private AccountService accountService;

    @Transactional
    public Transfer createTransfer(Transfer transfer) throws Exception{
        accountService.withdraw(transfer.getFromAccountNumber(),transfer.getAmount());
        accountService.deposit(transfer.getToAccountNumber(),transfer.getAmount());
        transferService.create(transfer);

        return transfer;
    }

    public Collection<Transfer> findAllTransfers() {

        return transferService.findAll();
    }

    public Transfer findTransferById(Long id){

        return transferService.findById(id);
    }
}
