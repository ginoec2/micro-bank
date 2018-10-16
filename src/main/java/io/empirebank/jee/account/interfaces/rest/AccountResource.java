package io.empirebank.jee.account.interfaces.rest;

import io.empirebank.jee.account.domain.model.Account;
import io.empirebank.jee.account.interfaces.facade.AccountServiceFacade;
import io.empirebank.jee.transfer.infrastructure.exception.ResourceNotFoundException;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.Optional;

@Path("/accounts")
public class AccountResource {


    @EJB
    private AccountServiceFacade accountServiceFacade;

    @GET
    @Produces
    public Response getAll() {

        Collection<Account> accounts = accountServiceFacade.findAllAccounts();

        return Response.ok(accounts).build();
    }

    @GET
    @Path("/{accountNumber}")
    @Produces
    public Response getAccountById(@PathParam(value="accountNumber") String accountNumber) throws Exception{
        Account account = Optional.ofNullable(accountServiceFacade.findAccountById(accountNumber)).orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        return Response.ok(account).build();

    }

}
