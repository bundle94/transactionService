package com.blueharvest.transactionservice.service;

import com.blueharvest.transactionservice.model.BaseResponse;
import com.blueharvest.transactionservice.model.CreateTransaction;
import com.blueharvest.transactionservice.model.Transaction;

import java.util.List;

public interface TransactionService {
    BaseResponse CreateTransaction(CreateTransaction request);
    BaseResponse<List<Transaction>> fetchTransactions(long accountId);
}
