package com.akhil.employeeservice.dao;

import com.akhil.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeDao extends PagingAndSortingRepository<Employee, Integer> {

}
