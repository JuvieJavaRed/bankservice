package com.wonderlabz.bankservice.controllers;

import com.wonderlabz.bankservice.dto.CreateUserDto;
import com.wonderlabz.bankservice.services.UserAccountManagement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ManageAccountsController {

    @Autowired
    private UserAccountManagement userAccountManagement;

    @PostMapping("/createaccount")
    public ResponseEntity createAccount(@RequestBody @Validated CreateUserDto createUserDto){
        log.info("Now processing account creation for ", createUserDto);
        return new ResponseEntity<>("Your account has been successfully created", HttpStatus.OK);
    }

    @GetMapping("/deactivateaccount/{email}")
    public ResponseEntity deActivateAccount(@PathVariable("email") String email){
        return new ResponseEntity<>("Your account has been successfully created", HttpStatus.OK);
    }
}
