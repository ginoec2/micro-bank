package io.empirebank.jee.account.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Account {

    @Id
    @Size(min = 5, max = 20)
    private String accountNumber;
    @NotNull
    private BigDecimal balance;
    @NotNull
    private LocalDateTime createdAt;
    @NotNull
    @Size(min = 5, max= 20)
    private String userId;

    public Account() {
    }

    public Account(@Size(min = 5, max = 20) String accountNumber, @NotNull BigDecimal balance,@NotNull LocalDateTime createdAt, @NotNull @Size(min = 5, max = 20) String userId) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
