package com.yaskal.customer.mapper;

import com.yaskal.customer.api.model.AuditResponse;
import com.yaskal.customer.model.db.Audit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuditResponseMapper {

    AuditResponse map(Audit request);
}
