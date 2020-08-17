package com.revature.bankProject0.exceptions;

import com.revature.bankProject0.services.LogService;

import java.util.logging.Logger;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String msg){
        super(msg);
        LogService.log(msg);
    }
}
