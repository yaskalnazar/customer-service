package com.yaskal.customer.api;

import com.yaskal.customer.api.model.CustomerRequest;
import com.yaskal.customer.api.model.CustomerResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/customers")
public interface CustomerApi {

    @PostMapping
    ResponseEntity<CustomerResponse> createCustomer(@RequestBody @Valid CustomerRequest request);

    @GetMapping("/{id}")
    ResponseEntity<CustomerResponse> getCustomer(@PathVariable Long id);

    @GetMapping
    ResponseEntity<Page<CustomerResponse>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name")
            @Pattern(regexp = "^(name|age|dateOfBirth|address)$", message = "Invalid sortBy field") String sortBy
    );

    @PutMapping("/{id}")
    ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerRequest request);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCustomer(@PathVariable Long id);

}
