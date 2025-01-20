package com.yaskal.customer.api;

import com.yaskal.customer.api.model.AuditResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/audits")
public interface AuditApi {

    @GetMapping
    ResponseEntity<Page<AuditResponse>> getAllAudits(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size
    );

}
