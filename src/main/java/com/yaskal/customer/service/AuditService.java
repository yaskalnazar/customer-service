package com.yaskal.customer.service;

import com.yaskal.customer.api.model.AuditAction;
import com.yaskal.customer.api.model.AuditResponse;
import com.yaskal.customer.api.model.OperationStatus;
import com.yaskal.customer.mapper.AuditResponseMapper;
import com.yaskal.customer.model.db.Audit;
import com.yaskal.customer.repository.AuditRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuditService  {

    public static final String CREATION_DATE_TIME = "creationDateTime";

    private AuditRepository auditRepository;
    private AuditResponseMapper auditResponseMapper;

    public void logAudit(AuditAction action, Long customerId, String request, OperationStatus status) {
        Audit audit = new Audit();
        audit.setAction(action);
        audit.setCustomerId(customerId);
        audit.setRequest(request);
        audit.setStatus(status);
        audit.setCreationDateTime(LocalDateTime.now());

        auditRepository.save(audit);
    }


    public Page<AuditResponse> getAllAudits(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(CREATION_DATE_TIME).descending());

        Page<Audit> responses = auditRepository.findAll(pageable);

        return responses.map(audit -> auditResponseMapper.map(audit));
    }


}
