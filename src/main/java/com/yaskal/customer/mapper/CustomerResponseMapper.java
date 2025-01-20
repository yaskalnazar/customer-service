package com.yaskal.customer.mapper;

import com.yaskal.customer.api.model.CustomerResponse;
import com.yaskal.customer.model.db.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerResponseMapper {

    CustomerResponse map(Customer request);
}
