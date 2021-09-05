package com.wonderlabz.bankservice.services;

import com.wonderlabz.bankservice.dto.CreateUserDto;
import com.wonderlabz.bankservice.entities.CurrentAccount;
import com.wonderlabz.bankservice.entities.SavingsAccount;
import com.wonderlabz.bankservice.entities.Users;
import com.wonderlabz.bankservice.util.BigDecimalConverter;
import com.wonderlabz.bankservice.util.GenerateAccountNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class UserAccountManagement {

    @Value("${bank.minimumDeposit}")
    private BigDecimal minimumDeposit;

    @Value("${bank.overDraftBalance}")
    private BigDecimal overDraftBalance;

    @Value("${bank.deposit}")
    private BigDecimal depoist;

    @Value("${bank.withdraw}")
    private BigDecimal withdraw;

    Users users = new Users();
    CurrentAccount currentAccount = new CurrentAccount();
    SavingsAccount savingsAccount = new SavingsAccount();
    BigDecimalConverter bigDecimalConverter = new BigDecimalConverter();

    /*
    * Method to create a new user account
    * @param createUserDto of type CreateUserDto
    * returns 1 if successfully created returns 0 if not
    */
    public int createNewUser(CreateUserDto createUserDto){
        int createUserDefinition = -1;
        log.info("We are now processing account creating process for potential new user : "+createUserDto.getEmail());
        BigDecimal clientDepositAmount = bigDecimalConverter.changeStringToBigDecimal(createUserDto.getSavingsAccountDepositAmount());
        GenerateAccountNumber generateAccountNumber = new GenerateAccountNumber();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime dateofBirth = LocalDateTime.parse(createUserDto.getDob(), df);
        if(clientDepositAmount.compareTo(minimumDeposit)==1||clientDepositAmount.compareTo(minimumDeposit)==0){
            savingsAccount.setSavingsAccountNumber(generateAccountNumber.generateSecureAcccountNumber());
            savingsAccount.setCurrentBalance(clientDepositAmount);
            savingsAccount.setDepositAmount(clientDepositAmount);
            savingsAccount.setDateOpened(LocalDateTime.now());
            savingsAccount.setTransactionDate(LocalDateTime.now());
            savingsAccount.setCurrentBalance(clientDepositAmount);
            savingsAccount.setOverdraftBalance(overDraftBalance);
            savingsAccount.setWithdrawnAmount(bigDecimalConverter.changeStringToBigDecimal("0"));
            currentAccount.setCurrentAccountNumber(generateAccountNumber.generateSecureAcccountNumber());
            currentAccount.setDepositAmount(bigDecimalConverter.changeStringToBigDecimal(createUserDto.getCurrentAccountDepositAmount()));
            currentAccount.setDateOpened(LocalDateTime.now());
            currentAccount.setCurrentBalance(bigDecimalConverter.changeStringToBigDecimal(createUserDto.getCurrentAccountDepositAmount()));
            currentAccount.setMaximumOverdraft(overDraftBalance);
            currentAccount.setOverdraftBalance(overDraftBalance);
            currentAccount.setTransactionDate(LocalDateTime.now());
            log.info("About to save user information");
            users.setEmail(createUserDto.getEmail());
            users.setSurname(createUserDto.getSurname());
            users.setName(createUserDto.getName());
            users.setDob(LocalDate.from(dateofBirth));
            users.setCurrentAccount(currentAccount);
            users.setSavingsAccount(savingsAccount);
            log.info("About to save new user {}", users.toString());
        }else{
            createUserDefinition = 0;
        }

        return createUserDefinition;
    }

    /*
     * Method to make user account inactive or active again
     * @param email
     * returns 1 if successfully created returns 0 if not
     */
    public int changeUserAccountStatus(){
        return 0;
    }
}
