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

import static com.yaskal.customer.api.model.AuditAction.CREATE;
import static com.yaskal.customer.api.model.AuditAction.DELETE;
import static com.yaskal.customer.api.model.AuditAction.UPDATE;
import static com.yaskal.customer.api.model.OperationStatus.FAILED;
import static com.yaskal.customer.api.model.OperationStatus.SUCCESS;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    private CustomerResponseMapper customerResponseMapper;
    private AuditService auditService;

    public CustomerResponse createCustomer(CustomerRequest request) {
        try {
            Customer customer = customerMapper.map(request);
            customer = customerRepository.save(customer);
            CustomerResponse response = customerResponseMapper.map(customer);

            auditService.logAudit(CREATE, customer.getId(), request.toString(), SUCCESS);
            return response;
        } catch (Exception ex) {
            auditService.logAudit(CREATE, null, request.toString(), FAILED);
            throw ex;
        }
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
        try {
            throwExceptionIfCustomerMissing(id);

            Customer updatedCustomer = customerMapper.map(request);
            updatedCustomer.setId(id);
            updatedCustomer = customerRepository.save(updatedCustomer);
            CustomerResponse response = customerResponseMapper.map(updatedCustomer);

            auditService.logAudit(CREATE, id, request.toString(), SUCCESS);
            return response;
        } catch (Exception ex) {
            auditService.logAudit(UPDATE, id, request.toString(), FAILED);
            throw ex;
        }
    }

    public void deleteCustomer(Long id) {
        try {
            throwExceptionIfCustomerMissing(id);
            customerRepository.deleteById(id);
            auditService.logAudit(DELETE, id, null, SUCCESS);
        } catch (Exception ex) {
            auditService.logAudit(DELETE, id, null, FAILED);
            throw ex;
        }
    }

    private void throwExceptionIfCustomerMissing(Long id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }
}
