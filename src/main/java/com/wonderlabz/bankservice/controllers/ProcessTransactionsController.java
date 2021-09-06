package com.wonderlabz.bankservice.controllers;


import com.wonderlabz.bankservice.dto.DepositMoneyDto;
import com.wonderlabz.bankservice.dto.WithdrawlDto;
import com.wonderlabz.bankservice.entities.Transactions;
import com.wonderlabz.bankservice.services.TransactionManagement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ProcessTransactionsController {

    @Autowired
    private TransactionManagement transactionManagement;

    @PostMapping("/makewithdrawal")
    public ResponseEntity makeWithDrawal(@RequestBody @Validated WithdrawlDto withdrawlDto){
        log.info("now depositing money for the user : "+withdrawlDto.getEmail());
        transactionManagement.withdrawMoney(withdrawlDto);
        return new ResponseEntity<>("Your successfully deposited", HttpStatus.OK);
    }

    @PostMapping("/makedeposit")
    public ResponseEntity makeDeposit(@RequestBody @Validated DepositMoneyDto depositMoneyDto){
        log.info("now depositing money for the user : "+depositMoneyDto.getEmail());
        transactionManagement.depositMoney(depositMoneyDto);
        return new ResponseEntity<>("Your successfully deposited", HttpStatus.OK);
    }

    @GetMapping("/retrivetransactions/{email}")
    public List<Transactions> retrieveTransactions(@PathVariable("email") String email){
        log.info("Retriving transactions for user : "+email);
        List<Transactions> transactionsList = transactionManagement.getTransactionHistory(email);
        return transactionsList;
    }
}
