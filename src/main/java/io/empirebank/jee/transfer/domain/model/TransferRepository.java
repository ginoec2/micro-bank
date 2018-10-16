package io.empirebank.jee.transfer.domain.model;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface TransferRepository extends EntityRepository<Transfer, Long> {
}
