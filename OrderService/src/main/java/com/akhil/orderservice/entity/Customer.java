package com.akhil.orderservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;


@Data
@NoArgsConstructor
public class Customer {
    private String customerId;
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String address;
    //private String street;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private String phone;
    private String fax;
}

