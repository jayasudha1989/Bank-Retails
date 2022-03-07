package com.example.bank.models;

import javax.persistence.*;
import java.time.LocalDateTime;

// TODO Add support for Bank charges, currency conversion, setup repeat payment/ standing order
@Entity
@Table(name = "transaction", schema = "online_bank")
public class Transaction {

    @Id
    private long id; 

    private long sourceAccountId;

    private long targetAccountId;

    private String targetOwnerName;

    private double amount;

    

    public Transaction() {}

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getSourceAccountId() {
        return sourceAccountId;
    }
    public void setSourceAccountId(long sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }
    public long getTargetAccountId() {
        return targetAccountId;
    }
    public void setTargetAccountId(long targetAccountId) {
        this.targetAccountId = targetAccountId;
    }
    public String getTargetOwnerName() {
        return targetOwnerName;
    }
    public void setTargetOwnerName(String targetOwnerName) {
        this.targetOwnerName = targetOwnerName;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
   

    @Override
    public String toString() {
        return "Transaction{" +
                "sourceAccountId=" + sourceAccountId +
                ", targetAccountId=" + targetAccountId +
                ", targetOwnerName='" + targetOwnerName + '\'' +
                ", amount=" + amount +
               
                '}';
    }
}
