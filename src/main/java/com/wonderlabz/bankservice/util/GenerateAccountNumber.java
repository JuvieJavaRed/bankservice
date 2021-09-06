package com.wonderlabz.bankservice.util;

import java.security.SecureRandom;

public class GenerateAccountNumber {

    public Long generateSecureAcccountNumber(){
        SecureRandom secureRandom = new SecureRandom();
        Long result = secureRandom.nextLong();
        return result;
    }
}
