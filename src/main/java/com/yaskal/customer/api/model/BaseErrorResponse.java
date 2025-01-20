package com.yaskal.customer.api.model;

import lombok.Getter;

import java.util.List;

@Getter
public class BaseErrorResponse {

    private int status;
    private String message;
    private List<String> errors;

    public BaseErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public BaseErrorResponse(int status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
}
