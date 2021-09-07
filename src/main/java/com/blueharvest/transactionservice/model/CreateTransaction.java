package com.blueharvest.transactionservice.model;

public class CreateTransaction {
    private long accountId;
    private double amount;

    public CreateTransaction (long accountId, double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
