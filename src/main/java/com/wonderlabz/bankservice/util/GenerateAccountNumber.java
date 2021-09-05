package com.wonderlabz.bankservice.util;

import java.security.SecureRandom;

public class GenerateAccountNumber {

    public Long generateSecureAcccountNumber(){
        SecureRandom secureRandom = new SecureRandom();
        Long result = secureRandom.nextLong();
        String accountNumber = result + "";
        if (accountNumber.length() != 13)
            for (int x = accountNumber.length(); x < 13; x++) accountNumber = "0" + accountNumber;


        return Long.getLong(accountNumber);
    }
}
