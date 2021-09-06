package com.blueharvest.transactionservice.controller;

import com.blueharvest.transactionservice.model.BaseResponse;
import com.blueharvest.transactionservice.model.CreateTransaction;
import com.blueharvest.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.concurrent.Callable;

@RestController
@RequestMapping(value = "api/v1/transactions")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    public Callable<ResponseEntity<BaseResponse>> createTransaction(@RequestBody CreateTransaction request) throws Exception {
        return () -> {
            BaseResponse response = transactionService.CreateTransaction(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        };
    }
}
