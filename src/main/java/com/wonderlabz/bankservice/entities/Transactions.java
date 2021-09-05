package com.wonderlabz.bankservice.entities;


import com.wonderlabz.bankservice.util.AccounTypes;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "transactions")
public class Transactions {
    @Id
    @Column(name="transactionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    @Column(name ="email")
    private String email;

    @Column(name="transactionDate")
    private LocalDateTime localDate;

    @Column (name="accountType")
    private AccounTypes accountType;

    @Column (name ="accountNumber")
    private long accountNumber;

    @Column(name="openingBalance")
    private BigDecimal openingBalance;

    @Column(name = "withdrawal")
    private BigDecimal withdrawal;

    @Column(name="deposit")
    private BigDecimal deposit;

    @Column(name = "balance")
    private BigDecimal balance;
}
