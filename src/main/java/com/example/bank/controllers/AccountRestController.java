package com.example.bank.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.example.bank.models.Account;
import com.example.bank.services.AccountService;
import com.example.bank.utils.AccountInput;
import com.example.bank.utils.InputValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class AccountRestController {

    private static final String NO_ACCOUNT_FOUND = "Unable to find an account  account number";

    private final AccountService accountService;

    @Autowired
    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }
    
  
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody String accountName, HttpServletRequest req) {
    	// Return the account details, if not create new account
    	Account account = accountService.patchAccount(accountName);

        if (account == null) {
            return new ResponseEntity(NO_ACCOUNT_FOUND, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(account, HttpStatus.OK);
        }
    }

    

   
    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ResponseEntity checkAccountBalance(@RequestBody String accountName, HttpServletRequest req) {
        // retrove acc information
	    Account account = accountService.getAccount(accountName);

        // Return the account details, or warn that no account was found for given input
        if (account == null) {
            return new ResponseEntity(NO_ACCOUNT_FOUND, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(account, HttpStatus.OK);
        }
    }

	
}
