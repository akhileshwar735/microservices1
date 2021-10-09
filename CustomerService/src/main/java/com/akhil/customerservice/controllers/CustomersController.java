package com.akhil.customerservice.controllers;

import com.akhil.customerservice.entity.ApiError;
import com.akhil.customerservice.entity.Customer;
import com.akhil.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/customer")
@RestController
public class CustomersController {
    @Autowired
    CustomerService service;

    @GetMapping(path = "/{id}", produces ="application/json")
    public ResponseEntity<Object> handleGetCustomerById(@PathVariable String id){
        Customer c=service.getcustomerById(id);
        if(c==null){
            ApiError err = new ApiError(HttpStatus.NOT_FOUND, "No product was found with id: " + id);
            System.out.println("ApiError Printing :"+err);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
        }
        else{
            return ResponseEntity.ok(c);
        }
    }

    @GetMapping(produces = "application/json")
    public Iterable<Customer> handleGetAllProducts(
            @RequestParam(name = "_page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "_limit", required = false, defaultValue = "10") Integer size
    ) {
        return service.getAllCustomers(page, size);
    }

    @PostMapping(produces = "application/json",consumes = "application/json")
    public ResponseEntity<Object> handleaddCustomer(@RequestBody Customer customer){
        try {
            Customer p = service.addNewCustomer(customer);
            return ResponseEntity.ok(p); // status=200
        } catch (Exception e) {
            ApiError err = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
        }}

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> handleUpdateCustomer(@PathVariable String id, @RequestBody Customer customer) {
        try {
            customer.setCustomerId(id);
            Customer p = service.updateCustomer(customer);
            return ResponseEntity.ok(p); // status=200
        } catch (Exception e) {
            ApiError err = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
        }
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Object> handleDeleteProduct(@PathVariable String id) {
        try {
            return ResponseEntity.ok(service.deleteCustomer(id)); // status=200
        } catch (Exception e) {
            ApiError err = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
        }
    }

}
