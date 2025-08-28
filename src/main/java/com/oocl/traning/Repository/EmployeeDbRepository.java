package com.oocl.traning.Repository;

import com.oocl.traning.Model.Employee;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDbRepository implements EmployeeRepository {

    JPAEmployeeRepository repository;

    @Override
    public Employee save(Employee newEemployee) {
        return repository.save(newEemployee);
    }

    @Override
    public Map<Integer, Employee> getAllEmployees() {
        return repository.findAll().stream().collect(java.util.stream.Collectors.toMap(Employee::getEmployeeID, employee -> employee));
    }

    @Override
    public Employee findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ArrayList<Employee> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public ArrayList<Employee> findGender(String gender) {
        //find by gender
        return repository.getEmployeesByGender(gender);
    }

    @Override
    public void updateEmployeeAgeandSalary(int id, int age, double salary) {
        Employee employee = repository.findById(id).orElse(null);
        if (employee != null) {
            employee.setAge(age);
            employee.setSalary(salary);
            repository.save(employee);
        }
    }

    @Override
    public List<Employee> getEmployeesByPage(int page, int pageSize) {
        return repository.findAll(PageRequest.of(page - 1, pageSize)).getContent();
    }

    @Override
    public void deleteEmployee(int id) {
        repository.deleteById(id);
    }

    @Override
    public void setAEmployee(Integer id, Employee employee) {
        employee.setEmployeeID(id);
        repository.save(employee);
    }

    @Override
    public void clear() {
        repository.deleteAll();
    }
}
