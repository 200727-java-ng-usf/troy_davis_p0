package com.revature.bankProject0.exceptions;

import com.revature.bankProject0.services.LogService;

public class AuthenticationException extends RuntimeException{
    public AuthenticationException(){
        super("User authentication failed!");
    }
    public AuthenticationException(String msg){
        super(msg);
        LogService.log(msg);
    }
}
