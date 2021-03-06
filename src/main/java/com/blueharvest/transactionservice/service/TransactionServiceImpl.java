package com.blueharvest.transactionservice.service;

import com.blueharvest.transactionservice.Exception.TransactionCreationException;
import com.blueharvest.transactionservice.model.BaseResponse;
import com.blueharvest.transactionservice.model.CreateTransaction;
import com.blueharvest.transactionservice.model.Transaction;
import com.blueharvest.transactionservice.repository.TransactionRepository;
import com.blueharvest.transactionservice.util.ResponseCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private TransactionRepository transactionRepository;

    @Autowired
    public  TransactionServiceImpl (TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public BaseResponse CreateTransaction(CreateTransaction request) {
        BaseResponse response = new BaseResponse();
        try {
            Transaction transaction = new Transaction(request.getAccountId(), request.getAmount(), new Date());
            transactionRepository.save(transaction);
            LOGGER.info("Transaction has been created successfully");
            response.setResponseCode(ResponseCodes.SUCCESS.getCode());
            response.setResponseMessage(ResponseCodes.SUCCESS.getMessage());
            return response;
        }
        catch (Exception ex) {
            LOGGER.error("Transaction creation failed " + ex.getMessage());
            throw new TransactionCreationException(ResponseCodes.TRANSACTION_NOT_CREATED.getMessage());
        }
    }

    @Override
    public BaseResponse<List<Transaction>> fetchTransactions(long accountId) {
        BaseResponse<List<Transaction>> response = new BaseResponse<>();
        // Fetching transaction by account id
        LOGGER.info("Fetching transactions by account ID");
        List<Transaction> transaction = transactionRepository.findAllByAccountId(accountId).orElse(null);
        response.setResponseCode(ResponseCodes.SUCCESS.getCode());
        response.setResponseMessage(ResponseCodes.SUCCESS.getMessage());
        response.setData(transaction);
        return response;

    }
}
