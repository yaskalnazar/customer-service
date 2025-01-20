package com.yaskal.customer.controller;

import com.yaskal.customer.api.CustomerApi;
import com.yaskal.customer.api.model.CustomerRequest;
import com.yaskal.customer.api.model.CustomerResponse;
import com.yaskal.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class CustomerController implements CustomerApi {

    private CustomerService customerService;

    @Override
    public ResponseEntity<CustomerResponse> createCustomer(CustomerRequest request) {
        CustomerResponse response = customerService.createCustomer(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CustomerResponse> getCustomer(Long id) {
        CustomerResponse response = customerService.getCustomer(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Page<CustomerResponse>> getAllCustomers(int page, int size, String sortBy) {
        Page<CustomerResponse> response = customerService.getAllCustomers(page, size, sortBy);
        return ResponseEntity.ok(response);
    }


    @Override
    public ResponseEntity<CustomerResponse> updateCustomer(Long id, CustomerRequest request) {
        CustomerResponse response = customerService.updateCustomer(id, request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}