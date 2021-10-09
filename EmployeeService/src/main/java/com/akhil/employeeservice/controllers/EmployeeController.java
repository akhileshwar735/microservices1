package com.akhil.employeeservice.controllers;

import com.akhil.employeeservice.entity.ApiError;
import com.akhil.employeeservice.entity.Employee;
import com.akhil.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/employee") // uniform interface for this resource
@RestController // instructs spring to create an instance of this class and keep in container
// also, handler mapping scans this object for all the handler functions (@RequestMapping, @GetMapping, ...)
public class EmployeeController {

    @Autowired // instruct spring to find an object of type EmployeeService and assign it to this reference.
    EmployeeService service;

    // http://localhost:5000/api/Employees/23
    @GetMapping(path = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<Object> handleGetEmployeeById(@PathVariable Integer id) {
        Employee p = service.getEmployeeById(id);
        if (p == null) {
            // respond with 404
            // return ResponseEntity.notFound().build();
            ApiError err = new ApiError(HttpStatus.NOT_FOUND, "No Employee was found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
        } else {
            // respond with 200
            return ResponseEntity.ok(p);
        }
    }

    @GetMapping(path = "/{id}", produces = "text/plain")
    public String handleGetEmployeeByIdAsString(@PathVariable Integer id) {
        return service.getEmployeeById(id).toString();
    }

    @GetMapping(produces = "application/json")
    public Iterable<Employee> handleGetAllEmployees(
            @RequestParam(name = "_page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "_limit", required = false, defaultValue = "10") Integer size
    ) {
        return service.getAllEmployees(page, size);
    }

    // @RequestMapping(method = RequestMethod.POST)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> handleAddNewEmployee(@RequestBody Employee Employee) {
        try {
            Employee p = service.addNewEmployee(Employee);
            return ResponseEntity.ok(p); // status=200
        } catch (Exception e) {
            ApiError err = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
        }
    }


    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> handleUpdateEmployee(@PathVariable Integer id, @RequestBody Employee Employee) {
        try {
            Employee.setEmployeeId(id); // override the payload's id with path-variable id
            Employee p = service.updateEmployee(Employee);
            return ResponseEntity.ok(p); // status=200
        } catch (Exception e) {
            ApiError err = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
        }
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Object> handleDeleteEmployee(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.deleteEmployee(id)); // status=200
        } catch (Exception e) {
            ApiError err = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
        }
    }

}
