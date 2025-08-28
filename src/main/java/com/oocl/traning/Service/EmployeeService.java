package com.oocl.traning.Service;

import com.oocl.traning.Model.Employee;
import com.oocl.traning.Repository.EmployeeDbRepository;
import com.oocl.traning.Repository.EmployeeRepository;
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

    public Employee getEmployeeById(int id) {
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

    public void updateEmployeeAgeandSalary(int id, int age, double salary) {
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

    public List<Employee> getEmployeesByPage(int page, int pageSize) {
        return employeeDbRepository.getEmployeesByPage(page, pageSize);
    }
    public void deleteEmployee(int id) {
        Employee employee= employeeDbRepository.findById(id);
        employee.setIsActive(false);
        employeeDbRepository.setAEmployee(id,employee);
    }
}
