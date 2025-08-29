package com.oocl.traning.service;

import com.oocl.traning.model.Employee;
import com.oocl.traning.repository.EmployeeDbRepository;
import com.oocl.traning.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
//    private final EmployeeMemoryRepository employeeMemoryRepository;

      private final EmployeeRepository employeeDbRepository;

    public EmployeeService(EmployeeDbRepository employeeDbRepository) {
        this.employeeDbRepository = employeeDbRepository;
    }

    public Employee createEmployee(Employee employee) {
        if(employee.getAge()>=65||employee.getAge()<=18){
            throw new IllegalArgumentException("Age must be between 18 and 65");
        }
        else if(employee.getAge()>=30 && employee.getSalary()<20000) {
            throw new IllegalArgumentException("Salary must be at least 20000 for employees over 30 years old");
        }
        else return employeeDbRepository.save(employee);
    }

    public Employee getEmployeeById(Integer id) {
        return employeeDbRepository.findById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeDbRepository.findAll();
    }
    public List<Employee> getEmployeesByGender(String gender)
    {
        if (gender == null || gender.isEmpty()) {
            return employeeDbRepository.findAll();// 返回所有员工
        }
        return employeeDbRepository.findGender(gender);
    }

    public void updateEmployeeAgeandSalary(Integer id, Integer age, Double salary) {
        Employee originEmployee= employeeDbRepository.findById(id);
        if(originEmployee.getIsActive()){
            originEmployee.setSalary(salary);
            originEmployee.setAge(age);

            if(originEmployee.getAge()>=65||originEmployee.getAge()<=18){
                throw new IllegalArgumentException("Age must be between 18 and 65");
            }
            else if(originEmployee.getAge()>=30 && originEmployee.getSalary()<20000) {
                throw new IllegalArgumentException("Salary must be at least 20000 for employees over 30 years old");
            }

            employeeDbRepository.setAEmployee(id,originEmployee);

        }
        else throw new IllegalArgumentException("Employee is not active");
    }

    public List<Employee> getEmployeesByPage(Integer page, Integer pageSize) {
        return employeeDbRepository.getEmployeesByPage(page, pageSize);
    }
    public void deleteEmployee(Integer id) {
        Employee employee= employeeDbRepository.findById(id);
        employee.setIsActive(false);
        employeeDbRepository.setAEmployee(id,employee);
    }
}
