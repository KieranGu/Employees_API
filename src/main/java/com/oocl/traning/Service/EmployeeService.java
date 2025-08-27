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

    public Employee createEmployee(Employee employee) {
        if(employee.getAge()>=65||employee.getAge()<=18){
            throw new IllegalArgumentException("Age must be between 18 and 65");
        }
        else if(employee.getAge()>=30 && employee.getSalary()<20000) {
            throw new IllegalArgumentException("Salary must be at least 20000 for employees over 30 years old");
        }
        else return employeeRepository.save(employee);
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
        Employee employee=getEmployeeById(id);
        if(employee.getIsActive()){
            employeeRepository.updateEmployeeAgeandSalary(id, age, salary);
        }
        else throw new IllegalArgumentException("Employee is not active");
    }

    public List<Employee> getEmployeesByPage(int page, int pageSize) {
        return employeeRepository.getEmployeesByPage(page, pageSize);
    }
    public void deleteEmployee(int id) {
        Employee employee=employeeRepository.findById(id);
        employee.setIsActive(false);
        employeeRepository.setAEmployee(id,employee);
    }
}
