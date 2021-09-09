package com.blueharvest.transactionservice.controller;

import com.blueharvest.transactionservice.model.BaseResponse;
import com.blueharvest.transactionservice.model.CreateTransaction;
import com.blueharvest.transactionservice.model.Transaction;
import com.blueharvest.transactionservice.service.TransactionService;
import com.blueharvest.transactionservice.service.TransactionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.Callable;

@RestController
@RequestMapping(value = "api/v1/transactions")
public class TransactionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);
    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    public Callable<ResponseEntity<BaseResponse>> createTransaction(@RequestBody CreateTransaction request) {
        LOGGER.info("Received request for creating transaction");
        return () -> {
            BaseResponse response = transactionService.CreateTransaction(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        };
    }

    @GetMapping("/fetch/{accountId}")
    public Callable<ResponseEntity<BaseResponse<List<Transaction>>>> GetTransaction(@PathVariable long accountId) {
        LOGGER.info("Received request for getting transactions by account");
        return () -> {
            BaseResponse<List<Transaction>> response = transactionService.fetchTransactions(accountId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        };
    }

}
