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


    private static final String INVALID_SEARCH_CRITERIA =
            "The provided sort code or account number did not match the expected format";

    private static final String NO_ACCOUNT_FOUND =
            "Unable to find an account matching this sort code and account number";

    private final AccountService accountService;

    @Autowired
    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }
    
  
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody String accountName, HttpServletRequest req) {
        
           
	    	    Account account = accountService.getAccount(accountName);
	
	            // Return the account details, or warn that no account was found for given input
	            if (account == null) {
	                return new ResponseEntity(NO_ACCOUNT_FOUND, HttpStatus.NO_CONTENT);
	            } else {
	                return new ResponseEntity(account, HttpStatus.OK);
	            }
		   
       
    }

    

   
    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public ResponseEntity checkAccountBalance(@RequestBody String accountName, HttpServletRequest req) {
            // Attempt to retrieve the account information
    	    Account account = accountService.getAccount(accountName);

            // Return the account details, or warn that no account was found for given input
            if (account == null) {
                return new ResponseEntity(NO_ACCOUNT_FOUND, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity(account, HttpStatus.OK);
            }
        
    }

	/*
	 * @ResponseStatus(HttpStatus.BAD_REQUEST)
	 * 
	 * @ExceptionHandler(MethodArgumentNotValidException.class) public Map<String,
	 * String> handleValidationExceptions( MethodArgumentNotValidException ex) {
	 * Map<String, String> errors = new HashMap<>();
	 * 
	 * ex.getBindingResult().getAllErrors().forEach((error) -> { String fieldName =
	 * ((FieldError) error).getField(); String errorMessage =
	 * error.getDefaultMessage(); errors.put(fieldName, errorMessage); });
	 * 
	 * return errors; }
	 */
}
