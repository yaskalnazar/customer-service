package com.yaskal.customer.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class CustomerResponse {

    private Long id;
    private String name;
    private int age;
    private LocalDate dateOfBirth;
    private String address;
    private Gender gender;


}