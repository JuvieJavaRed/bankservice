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
@Table(name = "savings_account")
public class SavingsAccount {
    @Id
    @Column(name = "savings_account_number")
    private long savingsAccountNumber;

    @Column(name = "date_opened")
    private LocalDateTime dateOpened;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "current_balance")
    private BigDecimal currentBalance;

    @Column(name = "depositAmount")
    private BigDecimal depositAmount;

    @Column(name = "withdrawnAmount")
    private BigDecimal withdrawnAmount;

    @Column(name = "overdraftBalance")
    private BigDecimal overdraftBalance;

    @Column(name = "minimumDeposit")
    private BigDecimal minimumDeposit;

    @Column(name = "minimumBalance")
    private  BigDecimal minimumBalance;
}
