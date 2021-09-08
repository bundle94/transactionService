package com.blueharvest.transactionservice.util;

public enum ResponseCodes {

    SUCCESS("S01", "Successful"),
    TRANSACTION_NOT_CREATED("E01", "Transaction cannot be created"),
    TRANSACTION_NOT_FOUND("E02", "Transaction not found");


    private String message;
    private String code;

    ResponseCodes(String code, String message){
        this.code = code;
        this.message = message;
    }
    public String getCode() { return this.code; }
    public String getMessage(){
        return this.message;
    }

}
