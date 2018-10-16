package io.empirebank.jee.transfer.infrastructure.exception;


import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String message){
        super(message);
    }
}
