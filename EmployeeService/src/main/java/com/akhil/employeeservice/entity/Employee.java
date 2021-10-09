package com.akhil.employeeservice.entity;
import lombok.Data;
import lombok.Generated;
import lombok.ToString;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;


@XmlRootElement // to authorize JAXB to convert object into xml
@Data
@Entity
@Table(name = "EMPLOYEES")
public class Employee {
    @Id
    @GeneratedValue(generator="increment")
    @Column(name="EMPLOYEE_ID")
    private Integer employeeId;
    @Column(name="LAST_NAME")
    private String lastName;
    @Column(name="FIRST_NAME")
    private String firstname;
    @Column(name="TITLE")
    private String title;
    @Column(name="TITLE_OF_COURTESY")
    private String titleOfCourtesy;
    @Column(name="BIRTHDATE")
    private Date birthDate;
    @Column(name="HIRE_DATE")
    private Date hireDate;
    @Column(name="ADDRESS")
    private String address;
    @Column(name="CITY")
    private String city;
    @Column(name="REGION")
    private String region;
    @Column(name ="POSTAL_CODE")
    private String postalCode;
    @Column(name="COUNTRY")
    private String country;
    @Column(name="HOME_PHONE")
    private String homePhone;
    @Column(name="EXTENSION")
    private String extension;
    @Column(name="REPORTS_TO")
    private Integer reportsTo;
}
