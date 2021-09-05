package com.wonderlabz.bankservice.dto;

import lombok.Data;

@Data
public class WithdrawlDto {
    private String email;
    private String accountType;
    private String amountToWithdraw;
}
