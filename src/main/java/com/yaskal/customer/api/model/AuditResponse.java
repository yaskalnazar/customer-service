package com.yaskal.customer.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class AuditResponse {

    private Long id;
    private AuditAction action;
    private Long customerId;
    private String request;
    private OperationStatus status;
    private LocalDateTime creationDateTime;

}