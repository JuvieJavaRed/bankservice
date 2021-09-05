package com.wonderlabz.bankservice.dto;

import lombok.Data;

@Data
public class CreateUserDto {
    public String email;
    public String name;
    public String surname;
    public String dob;
    public String phoneNumber;
    public String currentAccountDepositAmount;
    public String savingsAccountDepositAmount;
}
