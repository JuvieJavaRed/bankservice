package com.wonderlabz.bankservice.dto;

import lombok.Data;

@Data
public class DepositMoneyDto {
    public String amountToDeposit;
    public String email;
    public String accountType;
}
