package io.empirebank.jee.transfer.interfaces.rest;

import io.empirebank.jee.transfer.interfaces.dto.RequestTransfer;
import io.empirebank.jee.transfer.domain.model.Transfer;
import io.empirebank.jee.transfer.infrastructure.exception.ResourceNotFoundException;
import io.empirebank.jee.transfer.interfaces.facade.MoneyTransferServiceFacade;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Path("/transfers")
public class TransferResource {


    @EJB
    private MoneyTransferServiceFacade transferServiceFacade;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {

        Collection<Transfer> transferList = transferServiceFacade.findAllTransfers();

        return Response.ok(transferList).build();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam(value="id") @Valid @NotNull Long id) throws ResourceNotFoundException {

        Transfer transfer = Optional.ofNullable(transferServiceFacade.findTransferById(id)).orElseThrow(() -> new ResourceNotFoundException("Transfer not found"));

        return Response.ok(transfer).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid RequestTransfer reqTransfer) throws Exception{

        Transfer transfer = new Transfer(reqTransfer.getFromAccountNumber(),reqTransfer.getToAccountNumber(),reqTransfer.getAmount(),reqTransfer.getMessage(), LocalDateTime.now());
        transferServiceFacade.createTransfer(transfer);

        return Response.ok(transfer).status(Response.Status.CREATED).build();
    }
}
