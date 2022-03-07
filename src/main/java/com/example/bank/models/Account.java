package com.example.bank.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "account", schema = "online_bank")
public class Account {

    @Id
    private long id;

    private String accountNumber;

    private double currentBalance;

    private double owingBalance;

    private String ownerName;

    

    public Account() {}
    public Account(long id, String accountNumber, double currentBalance,double owingBalance, String ownerName) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.currentBalance = currentBalance;
        this.ownerName = ownerName;
        this.owingBalance = owingBalance;
    }
    public double getOwingBalance() {
		return owingBalance;
	}
	public void setOwingBalance(double owingBalance) {
		this.owingBalance = owingBalance;
	}
	public Account(long id,  String accountNumber, double currentBalance,  String ownerName, List<Transaction> transactions) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.currentBalance = currentBalance;
        this.ownerName = ownerName;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public double getCurrentBalance() {
        return currentBalance;
    }
    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
   
   

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", owingBal='" + owingBalance + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", currentBalance=" + currentBalance +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
