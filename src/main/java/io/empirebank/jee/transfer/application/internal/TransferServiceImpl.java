package io.empirebank.jee.transfer.application.internal;

import io.empirebank.jee.transfer.domain.model.Transfer;
import io.empirebank.jee.transfer.application.TransferService;
import io.empirebank.jee.transfer.domain.model.TransferRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TransferServiceImpl implements TransferService {

    @Inject
    private TransferRepository transferRepository;

    public Transfer create(Transfer transfer) {
        transferRepository.save(transfer);
        return transfer;
    }

    public List<Transfer> findAll() {
        return transferRepository.findAll();
    }

    public Transfer findById(Long id) {
        return transferRepository.findBy(id);
    }

}
