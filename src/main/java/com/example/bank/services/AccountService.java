package com.example.bank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.models.Account;
import com.example.bank.repositories.AccountRepository;
import com.example.bank.repositories.TransactionRepository;
import com.example.bank.utils.AccountInput;

import java.util.Optional;
import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Account patchAccount(String accountName) {
    	try {
    		Optional<Account> account = accountRepository.findByOwnerName(accountName);

	       if(!account.isPresent()) {
	    	   Account acc=new Account();
	    	   acc.setAccountNumber(randomAccNoGenerate());
	    	   acc.setOwnerName(accountName);
	    	   acc.setCurrentBalance(0);
	    	   acc.setOwingBalance(0);
	    	   
	    	   accountRepository.save(acc);
	       }
	       return account.orElse(null);
    	}catch(Exception e) {
    		System.out.println("Exceptin patchAccount:"+e.getMessage());
    	}
    	return null;
    }
    
    public Account getAccount(String accountName) {
    	try {
        Optional<Account> account = accountRepository
                .findByOwnerName(accountName);

        return account.orElse(null);
    	}catch(Exception e) {
    		System.out.println("Exceptin getAccount:"+e.getMessage());
    	}
    	return null;
    }
    
    public String randomAccNoGenerate() {
    	Random rnd = new Random();
        int number = rnd.nextInt(99999999);
        return String.format("%08d", number);
        
    	
    }
}
