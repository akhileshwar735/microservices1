package com.akhil.customerservice.entity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;


@Data
@XmlRootElement
@Entity
@Table(name="CUSTOMERS")
public class Customer {
//    @Id
//    @Column(name="ID")
//    @GeneratedValue(generator = "increment")
//    private Integer id;
    @Id
    @Column(name="CUSTOMER_ID")
    private String customerId;
    @Column(name="COMPANY_NAME")
    private String companyName;
    @Column(name="CONTACT_NAME")
    private String contactName;
    @Column(name="CONTACT_TITLE")
    private String contactTitle;
    @Column(name="ADDRESS")
    private String address;
    @Column(name="CITY")
    private String city;
    @Column(name="REGION")
    private String region;
    @Column(name="POSTAL_CODE")
    private String postalCode;
    @Column(name="COUNTRY")
    private String country;
    @Column(name="PHONE")
    private String phone;
    @Column(name="FAX")
    private String FAX;


}
