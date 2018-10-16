package io.empirebank.jee.transfer.application;

import io.empirebank.jee.transfer.domain.model.Transfer;

import java.util.Collection;

public interface TransferService {

    Transfer create(Transfer transfer);
    Transfer findById(Long id);
    Collection<Transfer> findAll();
}
