package com.blueharvest.transactionservice.service;

import com.blueharvest.transactionservice.Exception.TransactionCreationException;
import com.blueharvest.transactionservice.model.BaseResponse;
import com.blueharvest.transactionservice.model.CreateTransaction;
import com.blueharvest.transactionservice.model.Transaction;
import com.blueharvest.transactionservice.repository.TransactionRepository;
import com.blueharvest.transactionservice.util.ResponseCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    @Autowired
    public  TransactionServiceImpl (TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public BaseResponse CreateTransaction(CreateTransaction request) {
        BaseResponse response = new BaseResponse();
        Transaction transaction = new Transaction(request.getAccountId(), request.getAmount(), new Date());
        transactionRepository.save(transaction);
        if(transaction.getId() > 0) {
            response.setResponseCode(ResponseCodes.SUCCESS.getCode());
            response.setResponseMessage(ResponseCodes.SUCCESS.getMessage());
            return response;
        }
        throw new TransactionCreationException(ResponseCodes.TRANSACTION_NOT_CREATED.getMessage());
    }
}
