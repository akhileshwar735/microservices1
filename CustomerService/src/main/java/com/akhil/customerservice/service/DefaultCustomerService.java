package com.akhil.customerservice.service;

import com.akhil.customerservice.dao.CustomerDao;
import com.akhil.customerservice.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service("service")
public class DefaultCustomerService implements CustomerService{
    @Autowired
    CustomerDao dao;

    @Override
    public Customer addNewCustomer(Customer customer) {
        //customer.setCustomerId(null);
        return dao.save(customer);
    }


    @Override
    public Customer getcustomerById(String id) {
        return dao.findBycustomerId(id);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        if (customer.getCustomerId() == null) {
            throw new RuntimeException("Cannot update product without ID!");
        }
        return dao.save(customer);

    }


    @Override
    public Customer deleteCustomer(String id) {
        Customer p =dao.findBycustomerId(id);
        dao.delete(p);
        return p;
    }

    @Override
    public Iterable<Customer> getAllCustomers(Integer pageNum, Integer pageSize) {
            Page<Customer> result = dao.findAll(PageRequest.of(pageNum - 1, pageSize));
            return result.hasContent() ? result.getContent() : new ArrayList<>();
    }
}
