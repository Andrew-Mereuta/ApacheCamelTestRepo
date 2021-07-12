package com.example.beans;

import com.example.entities.Employee;
import com.example.repositories.EmployeeRepository;
import com.example.serializers.EmployeeSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component("employeeBean")
public class EmployeeBean {

    private EmployeeRepository employeeRepository;

    public EmployeeBean(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<Object> getEmployees() {
        List<Employee> all = employeeRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    public ResponseEntity<Object> createEmployee(Employee employee) {
        employee.setDate(LocalDate.now());
        employeeRepository.save(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Object> getEmployee(Long id) {
        Employee e = employeeRepository.findById(id).orElse(null);
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    public ResponseEntity<Object> patchEmployee(Long id, String name) {
        Employee e = employeeRepository.findById(id).orElse(null);
        if(e != null) {
            e.setName(name);
            employeeRepository.save(e);
        }
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
