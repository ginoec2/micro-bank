package io.empirebank.jee.account.infrastructure.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class AccountException extends Exception{

    public AccountException(String message){
        super(message);
    }
}
