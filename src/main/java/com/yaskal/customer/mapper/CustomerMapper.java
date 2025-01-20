package com.yaskal.customer.mapper;

import com.yaskal.customer.api.model.CustomerRequest;
import com.yaskal.customer.model.db.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer map(CustomerRequest request);
}
