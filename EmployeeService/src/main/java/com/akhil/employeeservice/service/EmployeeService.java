package com.akhil.employeeservice.service;


import com.akhil.employeeservice.entity.Employee;

public interface EmployeeService {

    // CRUD operations

    public Employee addNewEmployee(Employee Employee);

    public Employee getEmployeeById(Integer id);

    public Employee updateEmployee(Employee Employee);

    public Employee deleteEmployee(Integer id); // soft or hard delete ??

    // QUERY operations

    public Iterable<Employee> getAllEmployees(Integer pageNum, Integer pageSize);


}