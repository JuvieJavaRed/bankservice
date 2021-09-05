package com.wonderlabz.bankservice.entities;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "current_account")
public class CurrentAccount {
    @Id
    @Column(name = "current_account_number")
    private long currentAccountNumber;

    @Column(name = "date_opened")
    private LocalDateTime dateOpened;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "current_balance")
    private BigDecimal currentBalance;

    @Column(name = "deposit_amount")
    private BigDecimal depositAmount;

    @Column(name = "withdrawn_amount")
    private BigDecimal withdrawnAmount;

    @Column(name = "overdraft_balance")
    private BigDecimal overdraftBalance;

    @Column(name = "maximum_overdraft")
    private BigDecimal maximumOverdraft;
}
