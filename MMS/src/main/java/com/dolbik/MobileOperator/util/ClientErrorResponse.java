package com.dolbik.MobileOperator.util;

public class ClientErrorResponse {
    public String message;

    public ClientErrorResponse(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
