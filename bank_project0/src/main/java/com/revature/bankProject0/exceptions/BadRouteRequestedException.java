package com.revature.bankProject0.exceptions;

import com.revature.bankProject0.services.LogService;

public class BadRouteRequestedException extends RuntimeException {
    public BadRouteRequestedException(){
        super();
    }
    public BadRouteRequestedException(String msg){
        super(msg);
        LogService.log(msg);
    }
}
