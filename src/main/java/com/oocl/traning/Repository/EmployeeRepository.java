package com.oocl.traning.Repository;

import com.oocl.traning.Model.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface EmployeeRepository {
    Employee save(Employee newEemployee);

    Map<Integer, Employee> getAllEmployees();
    Employee findById(int id);
    ArrayList<Employee> findAll();
    ArrayList<Employee> findGender(String gender);
    void updateEmployeeAgeandSalary(int id, int age, double salary);
    List<Employee> getEmployeesByPage(int page, int pageSize);
    void deleteEmployee(int id);
    void setAEmployee(Integer id,Employee employee);
    void clear();

}
