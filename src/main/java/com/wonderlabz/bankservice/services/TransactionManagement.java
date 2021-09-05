package com.wonderlabz.bankservice.services;

import com.wonderlabz.bankservice.dto.DepositMoneyDto;
import com.wonderlabz.bankservice.dto.WithdrawlDto;
import com.wonderlabz.bankservice.entities.CurrentAccount;
import com.wonderlabz.bankservice.entities.SavingsAccount;
import com.wonderlabz.bankservice.entities.Transactions;
import com.wonderlabz.bankservice.entities.Users;
import com.wonderlabz.bankservice.repositories.CurrentAccountRepository;
import com.wonderlabz.bankservice.repositories.SavingsAccountRepository;
import com.wonderlabz.bankservice.repositories.TransactionsRepository;
import com.wonderlabz.bankservice.repositories.UsersRepository;
import com.wonderlabz.bankservice.util.AccounTypes;
import com.wonderlabz.bankservice.util.BigDecimalConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
public class TransactionManagement {

    @Value("${bank.minimumBalance}")
    private BigDecimal minimumBalance;

    @Value("${bank.minimumDeposit}")
    private BigDecimal minimumDeposit;

    @Value("${bank.withdraw}")
    private BigDecimal withdraw;

    @Value("${bank.overDraftBalance}")
    private BigDecimal overDraftBalance;

    @Value("${bank.deposit}")
    private BigDecimal depoist;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    SavingsAccountRepository savingsAccountRepository;

    @Autowired
    CurrentAccountRepository currentAccountRepository;



    public void withdrawFromSavings(WithdrawlDto withdrawlDto) {

        log.info("information on withdrawal amount");
        BigDecimalConverter bigDecimalConverter = new BigDecimalConverter();
        String accounttype = withdrawlDto.getAccountType();
        String email = withdrawlDto.getEmail();
        BigDecimal amountToWithdraw = bigDecimalConverter.changeStringToBigDecimal(withdrawlDto.getAmountToWithdraw());
        Users user = usersRepository.getById(email);
        if (accounttype.equals(AccounTypes.SAVINGSACCOUNT)) {
            SavingsAccount savingsAccount = user.getSavingsAccount();
            BigDecimal currentBalance = savingsAccount.getCurrentBalance();
            BigDecimal availableBalance = currentBalance.subtract(amountToWithdraw);
            if (availableBalance.compareTo(minimumDeposit)==1||availableBalance.compareTo(minimumDeposit)==0) {

                log.info("Available balance to be updated {}", availableBalance);
                savingsAccount.setCurrentBalance(availableBalance);
                log.info("About to update current balance");
                savingsAccountRepository.save(savingsAccount);

                setTransactionDetails(email, AccounTypes.valueOf(accounttype), currentBalance, depoist, amountToWithdraw, availableBalance);

            } else {

                log.info("insufficient funds");
            }
        } else if (accounttype.equals(AccounTypes.CURRENTACCOUNT)) {
            CurrentAccount currentAccount = user.getCurrentAccount();
            BigDecimal currentBalance = currentAccount.getCurrentBalance();
            if (currentBalance.subtract(amountToWithdraw).compareTo(BigDecimal.ZERO) == 1) {
                BigDecimal afterBalance = currentBalance.subtract(amountToWithdraw);
                log.info("Balance after withdrawing {}", afterBalance);
                currentAccount.setCurrentBalance(afterBalance);
                log.info("About to update balance");
                currentAccountRepository.save(currentAccount);

                setTransactionDetails(email, AccounTypes.valueOf(accounttype), currentBalance, depoist, amountToWithdraw, afterBalance);
            } else if (amountToWithdraw.compareTo(currentBalance.subtract(overDraftBalance)) == 1||amountToWithdraw.compareTo(currentBalance.subtract(overDraftBalance))==0) {

                BigDecimal afterBalance = currentBalance.negate().add(overDraftBalance).add(amountToWithdraw);
                log.info("Balance after withdrawing {}", afterBalance);
                currentAccount.setCurrentBalance(afterBalance);
                currentAccountRepository.save(currentAccount);
                log.info("About to save transaction details");
                setTransactionDetails(email, AccounTypes.valueOf(accounttype), currentBalance, depoist, amountToWithdraw, afterBalance);

            } else
                log.info("Insufficient Funds");

        }
        //transaction not possible
    }


    public void depositMoney(DepositMoneyDto depositMoneyDto) {
        log.info("received data to deposit money for : "+depositMoneyDto.getEmail());
        BigDecimalConverter bigDecimalConverter = new BigDecimalConverter();
        String accounttype = depositMoneyDto.getAccountType();
        String email = depositMoneyDto.getEmail();
        BigDecimal amountToDeposit = bigDecimalConverter.changeStringToBigDecimal(depositMoneyDto.getAmountToDeposit());
        Users user = usersRepository.getById(email);
        if (accounttype.equals(AccounTypes.CURRENTACCOUNT)) {
            CurrentAccount currentAccount = user.getCurrentAccount();
            BigDecimal currentBalance = currentAccount.getCurrentBalance();
            BigDecimal avaliableBalance = currentBalance.add(amountToDeposit);
            log.info("Available balance after deposit {}", avaliableBalance);
            //update balance
            currentAccount.setCurrentBalance(avaliableBalance);
            currentAccountRepository.save(currentAccount);


            setTransactionDetails(email, AccounTypes.valueOf(accounttype), currentBalance, amountToDeposit, withdraw, avaliableBalance);


        } else if (accounttype.equals(AccounTypes.SAVINGSACCOUNT)) {
            SavingsAccount savingsAccount = user.getSavingsAccount();
            BigDecimal currentBalance = savingsAccount.getCurrentBalance();
            BigDecimal avaliableBalance = currentBalance.add(amountToDeposit);
            log.info("Available balance after deposit {}", avaliableBalance);
            savingsAccount.setCurrentBalance(avaliableBalance);
            savingsAccountRepository.save(savingsAccount);
            setTransactionDetails(email, AccounTypes.valueOf(accounttype), currentBalance, amountToDeposit, withdraw, avaliableBalance);

        }


    }


    public List<Transactions> getTransactionHistory(String email) {
        log.info("This is the received email to be used to retrieve {}", email);
        List<Transactions> retrievedList = transactionsRepository.findByEmail(email);
        log.info("This is the retrieved list size {}", retrievedList.size());
        return retrievedList;
    }

    public Transactions setTransactionDetails(String email, AccounTypes accounttype, BigDecimal currentBalance, BigDecimal depositAmount, BigDecimal amountToWithdraw, BigDecimal afterBalance) {
        Transactions transactions = new Transactions();
        transactions.setEmail(email);
        transactions.setLocalDate(LocalDateTime.now());
        transactions.setAccountType(accounttype);
        transactions.setOpeningBalance(currentBalance);
        transactions.setDeposit(depositAmount);
        transactions.setWithdrawal(amountToWithdraw);
        transactions.setBalance(afterBalance);
        return transactionsRepository.save(transactions);


    }
}
