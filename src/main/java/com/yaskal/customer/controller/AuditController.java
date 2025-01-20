package com.yaskal.customer.controller;

import com.yaskal.customer.api.AuditApi;
import com.yaskal.customer.api.model.AuditResponse;
import com.yaskal.customer.service.AuditService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuditController implements AuditApi {

    private AuditService auditService;

    @Override
    public ResponseEntity<Page<AuditResponse>> getAllAudits(int page, int size) {
        Page<AuditResponse> audits = auditService.getAllAudits(page, size);
        return ResponseEntity.ok(audits);
    }
}
