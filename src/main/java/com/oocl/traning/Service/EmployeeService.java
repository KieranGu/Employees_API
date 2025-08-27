package com.oocl.traning.Service;

import com.oocl.traning.Model.Employee;
import com.oocl.traning.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    public List<Employee> getEmployeesByGender(String gender)
    {
        if (gender == null || gender.isEmpty()) {
            return employeeRepository.findAll();// 返回所有员工
        }
        return employeeRepository.findGender(gender);
    }

    public void updateEmployeeAgeandSalary(int id, int age, double salary) {
        employeeRepository.updateEmployeeAgeandSalary(id, age, salary);
    }

    public List<Employee> getEmployeesByPage(int page, int pageSize) {
        return employeeRepository.getEmployeesByPage(page, pageSize);
    }
    public void deleteEmployee(int id) {
        employeeRepository.deleteEmployee(id);
    }
}
