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
import com.example.bank.services.TransactionService;
import com.example.bank.utils.AccountInput;
import com.example.bank.utils.InputValidator;
import com.example.bank.utils.TransactionInput;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class TransactionRestController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionRestController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    
    @RequestMapping(value = "/transactions", method = RequestMethod.POST)
    public ResponseEntity checkAccountBalance(@RequestBody TransactionInput transactionInput, HttpServletRequest req) {
            boolean isComplete = transactionService.makeTransfer(transactionInput);
            return new ResponseEntity(isComplete, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/topup", method = RequestMethod.POST)
    public ResponseEntity checkAccountBalance(@RequestBody AccountInput accountInput, HttpServletRequest req) {
    	    
            boolean isComplete = transactionService.topAupAccBal(accountInput);
            return new ResponseEntity(isComplete, HttpStatus.OK);
    }

}
