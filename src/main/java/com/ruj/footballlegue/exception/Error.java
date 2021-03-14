package com.ruj.footballlegue.exception;

import lombok.Data;

@Data
public class Error {
    private String errorCode;
    private String errorMsg;

    public Error(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
