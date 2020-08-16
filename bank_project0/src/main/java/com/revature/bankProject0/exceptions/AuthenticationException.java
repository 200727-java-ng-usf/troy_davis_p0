package com.revature.bankProject0.exceptions;

public class AuthenticationException extends RuntimeException{
    public AuthenticationException(){
        super();
    }
    public AuthenticationException(String msg){
        super(msg);
    }
}
