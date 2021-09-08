package com.blueharvest.transactionservice.Exception;

public class TransactionNotFoundException extends RuntimeException{
    private String code;

    public TransactionNotFoundException(String message) {
        super(message);
    }

    public TransactionNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
