package com.akhil.customerservice.service;

import com.akhil.customerservice.entity.Customer;


public interface CustomerService {
    public Customer addNewCustomer(Customer customer);

    //public Customer getCustomerById(Integer id);

    public Customer getcustomerById(String id);

    public Customer updateCustomer(Customer customer);

    public Customer deleteCustomer(String id); // soft or hard delete ??

    // QUERY operations

    public Iterable<Customer> getAllCustomers(Integer pageNum, Integer pageSize);


}
