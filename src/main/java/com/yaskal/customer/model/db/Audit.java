package com.yaskal.customer.model.db;

import com.yaskal.customer.api.model.AuditAction;
import com.yaskal.customer.api.model.OperationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private AuditAction action;

    private Long customerId;

    private String request;

    @Column(nullable = false)
    private OperationStatus status;

    @Column(nullable = false)
    private LocalDateTime creationDateTime;

}