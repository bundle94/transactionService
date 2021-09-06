package com.blueharvest.transactionservice.service;

import com.blueharvest.transactionservice.model.BaseResponse;
import com.blueharvest.transactionservice.model.CreateTransaction;

public interface TransactionService {
    BaseResponse CreateTransaction(CreateTransaction request);
}
