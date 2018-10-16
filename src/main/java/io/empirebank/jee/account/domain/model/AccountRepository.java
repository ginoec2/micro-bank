package io.empirebank.jee.account.domain.model;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface AccountRepository extends EntityRepository<Account, String> {
}
