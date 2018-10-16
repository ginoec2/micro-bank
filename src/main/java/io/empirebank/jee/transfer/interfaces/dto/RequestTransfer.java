package io.empirebank.jee.transfer.interfaces.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class RequestTransfer {

    @NotNull
    @Size(min = 5, max = 20)
    private String fromAccountNumber;
    @NotNull
    @Size(min = 5, max = 20)
    private String toAccountNumber;
    @NotNull
    private BigDecimal amount;
    @Size(max = 50)
    private String message;

    public RequestTransfer() {
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
