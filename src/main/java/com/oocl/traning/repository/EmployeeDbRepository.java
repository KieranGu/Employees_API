package com.oocl.traning.repository;

import com.oocl.traning.model.Employee;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDbRepository implements EmployeeRepository {


    private final JPAEmployeeRepository jpaEmployeeRepository;

    public EmployeeDbRepository(JPAEmployeeRepository jpaEmployeeRepository) {
        this.jpaEmployeeRepository = jpaEmployeeRepository;
    }

    @Override
    public Employee save(Employee newEemployee) {
        return jpaEmployeeRepository.save(newEemployee);
    }

    @Override
    public Map<Integer, Employee> getAllEmployees() {
        return jpaEmployeeRepository.findAll().stream().collect(java.util.stream.Collectors.toMap(Employee::getEmployeeId, employee -> employee));
    }

    @Override
    public Employee findById(int id) {
        return jpaEmployeeRepository.findById(id).orElse(null);
    }

    @Override
    public ArrayList<Employee> findAll() {
        return new ArrayList<>(jpaEmployeeRepository.findAll());
    }

    @Override
    public ArrayList<Employee> findGender(String gender) {
        //find by gender
        return jpaEmployeeRepository.getEmployeesByGender(gender);
    }

    @Override
    public void updateEmployeeAgeandSalary(int id, int age, double salary) {
        Employee employee = jpaEmployeeRepository.findById(id).orElse(null);
        if (employee != null) {
            employee.setAge(age);
            employee.setSalary(salary);
            jpaEmployeeRepository.save(employee);
        }
    }

    @Override
    public List<Employee> getEmployeesByPage(int page, int pageSize) {
        return jpaEmployeeRepository.findAll(PageRequest.of(page - 1, pageSize)).getContent();
    }

    @Override
    public void deleteEmployee(int id) {
        jpaEmployeeRepository.deleteById(id);
    }

    @Override
    public void setAEmployee(Integer id, Employee employee) {
        employee.setEmployeeId(id);
        jpaEmployeeRepository.save(employee);
    }

    @Override
    public void clear() {
        jpaEmployeeRepository.deleteAll();
    }
}
