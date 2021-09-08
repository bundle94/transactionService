package com.blueharvest.transactionservice.service;

import com.blueharvest.transactionservice.model.BaseResponse;
import com.blueharvest.transactionservice.model.CreateTransaction;
import com.blueharvest.transactionservice.model.Transaction;

public interface TransactionService {
    BaseResponse CreateTransaction(CreateTransaction request);
    BaseResponse<Transaction> fetchTransactions(long accountId);
}
