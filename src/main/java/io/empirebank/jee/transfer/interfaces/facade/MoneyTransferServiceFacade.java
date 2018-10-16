package io.empirebank.jee.transfer.interfaces.facade;

import io.empirebank.jee.transfer.domain.model.Transfer;

import java.util.Collection;

public interface MoneyTransferServiceFacade {

    Transfer createTransfer(Transfer transfer) throws Exception;
    Collection findAllTransfers();
    Transfer findTransferById(Long id);

}
