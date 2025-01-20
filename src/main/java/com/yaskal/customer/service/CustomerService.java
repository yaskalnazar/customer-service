package com.yaskal.customer.service;

import com.yaskal.customer.api.model.CustomerRequest;
import com.yaskal.customer.api.model.CustomerResponse;
import com.yaskal.customer.exceptions.model.CustomerNotFoundException;
import com.yaskal.customer.mapper.CustomerMapper;
import com.yaskal.customer.mapper.CustomerResponseMapper;
import com.yaskal.customer.model.db.Customer;
import com.yaskal.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    private CustomerResponseMapper customerResponseMapper;


    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer customer = customerMapper.map(request);
        customer = customerRepository.save(customer);
        return customerResponseMapper.map(customer);
    }

    public CustomerResponse getCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        return customerResponseMapper.map(customer);
    }

    public Page<CustomerResponse> getAllCustomers(int page, int size, String sortBy) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

        Page<Customer> customersPage = customerRepository.findAll(pageable);

        return customersPage.map(customer -> customerResponseMapper.map(customer));
    }

    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {
        throwExceptionIfCustomerMissing(id);

        Customer updatedCustomer = customerMapper.map(request);
        updatedCustomer.setId(id);

        updatedCustomer = customerRepository.save(updatedCustomer);
        return customerResponseMapper.map(updatedCustomer);
    }

    public void deleteCustomer(Long id) {
        throwExceptionIfCustomerMissing(id);
        customerRepository.deleteById(id);
    }

    private void throwExceptionIfCustomerMissing(Long id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }
}
