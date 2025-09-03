package com.oocl.traning.repository;

import com.oocl.traning.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface EmployeeRepository {
    Employee save(Employee newEemployee);

    Map<Integer, Employee> getAllEmployees();
    Employee findById(Integer id);
    ArrayList<Employee> findAll();
    ArrayList<Employee> findGender(String gender);
    void updateEmployeeAgeandSalary(Integer id, Integer age, Double salary);
    List<Employee> getEmployeesByPage(Integer page, Integer pageSize);
    void deleteEmployee(Integer id);
    void setAEmployee(Integer id,Employee employee);
    void clear();

}
