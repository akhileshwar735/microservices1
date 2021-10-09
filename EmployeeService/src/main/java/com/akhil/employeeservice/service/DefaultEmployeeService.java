package com.akhil.employeeservice.service;

import com.akhil.employeeservice.dao.EmployeeDao;
import com.akhil.employeeservice.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service("service")
// instructs spring to create an object of this class and keep in the container with the name "service"
public class DefaultEmployeeService implements EmployeeService {

    // dependency
    @Autowired
    EmployeeDao dao;

    @Override
    public Employee addNewEmployee(Employee Employee) {
        // ensure that Employee's id is null, so that JPA can execute an SQL INSERT command
        Employee.setEmployeeId(null);
        return dao.save(Employee); // SQL INSERT is executed
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        Optional<Employee> op = dao.findById(id);
        return op.isPresent() ? op.get() : null;
    }

    @Override
    public Employee updateEmployee(Employee Employee) {
        // prefer custom exception over RuntimeException
        if (Employee.getEmployeeId() == null) {
            throw new RuntimeException("Cannot update Employee without ID!");
        }
        if (!dao.existsById(Employee.getEmployeeId())) {
            throw new RuntimeException("Cannot update Employee. No data found for id " + Employee.getEmployeeId());
        }

        return dao.save(Employee); // SQL UPDATE is executed
    }

    @Override
    public Employee deleteEmployee(Integer id) {
        if (id == null || !dao.existsById(id)) {
            throw new RuntimeException("ID is either null or does not exist in our database");
        }
        // soft delete
//        Employee p = dao.findById(id).get();
//        return dao.save(p); // SQL UPDATE is executed to set DISCONTINUED=true

//         hard delete
         Employee p = dao.findById(id).get();
         dao.delete(p); // SQL DELETE is executed and the record is permanently deleted from the table
         return p;
    }

    @Override
    public Iterable<Employee> getAllEmployees(Integer pageNum, Integer pageSize) {
        Page<Employee> result = dao.findAll(PageRequest.of(pageNum - 1, pageSize));
        return result.hasContent() ? result.getContent() : new ArrayList<>();
        // return result;
    }


}