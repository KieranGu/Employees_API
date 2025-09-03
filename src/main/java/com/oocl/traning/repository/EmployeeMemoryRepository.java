package com.oocl.traning.repository;

import com.oocl.traning.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EmployeeMemoryRepository implements EmployeeRepository {
    private final Map<Integer, Employee> allEmployees = new HashMap<>(Map.of(
            1, new Employee(1, "John Smith", 32, "MALE", 5000.0),
            2, new Employee(2, "Jane Johnson", 28, "FEMALE", 6000.0),
            3, new Employee(3, "David Williams", 35, "MALE", 5500.0),
            4, new Employee(4, "Emily Brown", 23, "FEMALE", 4500.0),
            5, new Employee(5, "Michael Jones", 40, "MALE", 7000.0)));

    @Override
    public Employee save(Employee newEemployee) {
        int id;
        if (!allEmployees.isEmpty()) {
            id= Collections.max(allEmployees.keySet()) + 1;
        }
        else id=1;
        newEemployee.setEmployeeId(id);
        return allEmployees.put(newEemployee.getEmployeeId(), newEemployee);
    }
    @Override
    public Map<Integer, Employee> getAllEmployees() {
        return allEmployees;
    }
    @Override
    public Employee findById(Integer id) {
        return allEmployees.get(id);
    }
    @Override
    public ArrayList<Employee> findAll() {
        return new ArrayList<>(allEmployees.values());
    }
    @Override
    public ArrayList<Employee> findGender(String gender){
        ArrayList<Employee> employees = new ArrayList<>(allEmployees.values());
        ArrayList<Employee> genderEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (Objects.equals(employee.getGender(), gender)) {
                genderEmployees.add(employee);
            }
        }
        return genderEmployees;
    }
    @Override
    public void updateEmployeeAgeandSalary(Integer id, Integer age, Double salary)
    {
        Employee employee = allEmployees.get(id);
        if (employee != null) {
            employee.setAge(age);
            employee.setSalary(salary);
        }
    }
    @Override
    public List<Employee> getEmployeesByPage(Integer page, Integer pageSize) {
        List<Employee> employees = new ArrayList<>(allEmployees.values());
        Integer fromIndex = (page - 1) * pageSize;
        Integer toIndex = Math.min(fromIndex + pageSize, employees.size());
        if (fromIndex >= employees.size() || fromIndex < 0) {
            return Collections.emptyList();
        }
        return employees.subList(fromIndex, toIndex);
    }
    @Override
    public void deleteEmployee(Integer id) {
        Employee employee=allEmployees.get(id);
        employee.setIsActive(false);
        allEmployees.put(id, employee);
    }
    @Override
    public void setAEmployee(Integer id,Employee employee)
    {
        allEmployees.put(id,employee);
    }
    @Override
    public void clear() {
        allEmployees.clear();
    }
}
