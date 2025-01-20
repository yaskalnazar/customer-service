package com.yaskal.customer.exceptions.model;

public class CustomerNotFoundException extends CustomerBaseException {
    private static final String DEFAULT_MESSAGE = "Customer not found for id: ";
    public CustomerNotFoundException(Long id) {
        super(DEFAULT_MESSAGE + id);
    }
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
