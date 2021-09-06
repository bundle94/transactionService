package com.blueharvest.transactionservice.Exception;

public class TransactionCreationException extends RuntimeException {

    private String code;

    public TransactionCreationException(String message) {
        super(message);
    }

    public TransactionCreationException(String code, String message) {
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
