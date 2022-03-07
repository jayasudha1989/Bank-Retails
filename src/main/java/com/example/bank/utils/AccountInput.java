package com.example.bank.utils;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class AccountInput {

   

    @NotBlank(message = "Account number is mandatory")
    private String accountNumber;
    
    @NotBlank(message = "Account name is mandatory")
    private String accountName;
    
    @NotBlank(message = "Amount is mandatory")
    private double amount;

    public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	


	public AccountInput() {}

    
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "AccountInput{" +
        		"accountName='" + accountName + '\'' +
        		",amount='" + amount + '\'' +
                ",accountNumber='" + accountNumber + '\'' +
                '}';
    }

   


	
}
