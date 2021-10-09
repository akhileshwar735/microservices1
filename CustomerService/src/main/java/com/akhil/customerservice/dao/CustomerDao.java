package com.akhil.customerservice.dao;

import com.akhil.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Id;


@Repository("dao")
public interface CustomerDao extends PagingAndSortingRepository<Customer, Integer> {

    public Customer findBycustomerId(String id);

//    @Query("INSERT into CUSTOMERS VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)",c.)
//    public default Customer addNewCustomer(Customer c) {
//        return null;
//    }

}
