package com.revature.bankProject0.exceptions;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String msg){
        super(msg);
    }
}
