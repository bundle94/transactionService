package com.blueharvest.transactionservice.Exception;

import com.blueharvest.transactionservice.model.BaseResponse;
import com.blueharvest.transactionservice.model.ErrorResponse;
import com.blueharvest.transactionservice.util.ResponseCodes;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvise {

    @ExceptionHandler({TransactionCreationException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleTransactionCreationException(TransactionCreationException e) {
        BaseResponse res = new BaseResponse();
        res.setResponseCode(StringUtils.hasText(e.getCode()) ? e.getCode() : ResponseCodes.TRANSACTION_NOT_CREATED.getCode());
        res.setResponseMessage(e.getMessage());
        return new ErrorResponse(res);

    }

    @ExceptionHandler({TransactionNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleTransactionNotFoundException(TransactionNotFoundException e) {
        BaseResponse res = new BaseResponse();
        res.setResponseCode(StringUtils.hasText(e.getCode()) ? e.getCode() : ResponseCodes.TRANSACTION_NOT_FOUND.getCode());
        res.setResponseMessage(e.getMessage());
        return new ErrorResponse(res);

    }
}
