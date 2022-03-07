package com.example.bank.services;

import ch.qos.logback.core.net.SyslogOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.models.Account;
import com.example.bank.models.Transaction;
import com.example.bank.repositories.AccountRepository;
import com.example.bank.repositories.TransactionRepository;
import com.example.bank.utils.AccountInput;
import com.example.bank.utils.TransactionInput;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public boolean makeTransfer(TransactionInput transactionInput) {
      
       
        String sourceAccountNumber = transactionInput.getSourceAccount().getAccountNumber();
        Optional<Account> sourceAccount = accountRepository
                .findByAccountNumber(sourceAccountNumber);
         System.out.println("Source Account:"+sourceAccount.get());
       
        String targetAccountNumber = transactionInput.getTargetAccount().getAccountNumber();
        Optional<Account> targetAccount = accountRepository
                .findByAccountNumber(targetAccountNumber);
        System.out.println("Target Account:"+targetAccount.get());

        if (sourceAccount.isPresent() && targetAccount.isPresent()) {
           
        	Transaction transaction = new Transaction();

                transaction.setAmount(transactionInput.getAmount());
                transaction.setSourceAccountId(sourceAccount.get().getId());
                transaction.setTargetAccountId(targetAccount.get().getId());
                transaction.setTargetOwnerName(targetAccount.get().getOwnerName());
                
                Account sourceAcc = new Account();
                Account targetAcc = new Account();
                
                sourceAcc=sourceAccount.get();
                targetAcc=targetAccount.get();
                
                transactionRepository.save(transaction);

                updateSourceTransferBalance(sourceAcc, transactionInput.getAmount());
                updateTargetTransferBalance(targetAcc, transactionInput.getAmount());
                
              
                return true;
            }
        
        return false;
    }
    
    
    
    public boolean topAupAccBal(AccountInput accountInput) {
    	System.out.println("Account Bal topup.....");
    	double currBal =0D;
    	double owing =0D;
    	double amount=0D;
    	try {
    		 Optional<Account> accountOp = accountRepository.findByOwnerName(accountInput.getAccountName());
    		 Account account = accountOp.get();
    		 amount = accountInput.getAmount();
    		 System.out.println("topup bal amt:"+amount);
        	if((account.getOwingBalance()) > 0) {
        		if((amount-account.getOwingBalance()) > 0) {
        			double amt = Math.abs((amount-account.getOwingBalance()));
            		account.setCurrentBalance(amt);
            		account.setOwingBalance(0D);
        		}else {
        			double amt = Math.abs((amount-account.getOwingBalance()));
            		//account.setCurrentBalance(account.getCurrentBalance());
            		account.setOwingBalance(amt);
        		}
        	}else {
        		double amt = Math.abs((account.getCurrentBalance() + amount));
        		account.setCurrentBalance(amt);
        		account.setOwingBalance(0D);
        	}
        	System.out.println("currentBal:"+account.getCurrentBalance());
        	System.out.println("owingBal:"+account.getOwingBalance());
        	accountRepository.save(account);
        	return true;
    	}catch(Exception e) {
    		System.out.println("Account Bal Exception....."+e.getMessage());
    	}
    	return false;
    }

    private void updateSourceTransferBalance(Account account, double amount) {
    	double currBal =0D;
    	double owing =0D;
    	try {
	    	if((account.getCurrentBalance() - amount) >= 0) {
	    		account.setCurrentBalance((account.getCurrentBalance() - amount));
	    		account.setOwingBalance(0D);
	    		
	    	}else {
	    		owing = Math.abs((account.getCurrentBalance() + amount));
	    		System.out.println("updateSourceTransferBalance currentBal:"+owing);
	    		double amt = owing+account.getOwingBalance();
	    		
	    		account.setCurrentBalance(0);
	    		account.setOwingBalance(amt);
	    	}
	    	
	    	System.out.println("currentBal:"+account.getCurrentBalance());
	    	System.out.println("owingBal:"+account.getOwingBalance());
	    	accountRepository.save(account);
    	}catch(Exception e) {
    		System.out.println("Exception updateTargetTransferBalance:"+e.getMessage());
    	}
    	
    }
    
    private void updateTargetTransferBalance(Account targetAccount, double amount) {
    	double currBal =0D;
    	double owing =0D;
    	double amt = 0D;
    	try {
	    	if(targetAccount.getOwingBalance() >= 0) {
	    		if((amount-targetAccount.getOwingBalance())>=0){
	    			amt = Math.abs((amount-targetAccount.getOwingBalance()));
	    			double total = amt+targetAccount.getCurrentBalance();
	    			
	    			targetAccount.setCurrentBalance(total);
	    			targetAccount.setOwingBalance(0D);
	    		}else {
	    			amt = Math.abs((amount-targetAccount.getOwingBalance()));
	    			double total = amt;
	    			
	    			targetAccount.setCurrentBalance(targetAccount.getCurrentBalance());
	    			targetAccount.setOwingBalance(total);
	    		}
	    		System.out.println("target owing bal:"+owing);
	    	}else {
	    		double total = amt+targetAccount.getCurrentBalance();
				
				targetAccount.setCurrentBalance(total);
				targetAccount.setOwingBalance(0D);
	    	}
	    	
	    	System.out.println("currentBal:"+targetAccount.getCurrentBalance());
	    	System.out.println("owingBal:"+targetAccount.getOwingBalance());
	    	accountRepository.save(targetAccount);
    	}catch(Exception e) {
    		System.out.println("Exception updateTargetTransferBalance:"+e.getMessage());
    	}
    	
    	
    }

    
}
