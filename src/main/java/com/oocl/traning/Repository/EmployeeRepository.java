package com.oocl.traning.Repository;

import com.oocl.traning.Model.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EmployeeRepository {
    private final Map<Integer, Employee> allEmployees = new HashMap<>(Map.of(
            1, new Employee(1, "John Smith", 32, "MALE", 5000.0),
            2, new Employee(2, "Jane Johnson", 28, "FEMALE", 6000.0),
            3, new Employee(3, "David Williams", 35, "MALE", 5500.0),
            4, new Employee(4, "Emily Brown", 23, "FEMALE", 4500.0),
            5, new Employee(5, "Michael Jones", 40, "MALE", 7000.0)));


    public void save(Employee newEemployee) {
        int id= Collections.max(allEmployees.keySet()) + 1;
        newEemployee.setEmployeeID(id);
        allEmployees.put(newEemployee.getEmployeeID(), newEemployee);
    }
    public Map<Integer, Employee> getAllEmployees() {
        return allEmployees;
    }
    public Employee findById(int id) {
        return allEmployees.get(id);
    }
    public ArrayList<Employee> findAll() {
        return new ArrayList<>(allEmployees.values());
    }
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
    public void updateEmployeeAgeandSalary(int id, int age, double salary)
    {
        Employee employee = allEmployees.get(id);
        if (employee != null) {
            employee.setAge(age);
            employee.setSalary(salary);
        }
    }
    public List<Employee> getEmployeesByPage(int page, int pageSize) {
        List<Employee> employees = new ArrayList<>(allEmployees.values());
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, employees.size());
        if (fromIndex >= employees.size() || fromIndex < 0) {
            return Collections.emptyList();
        }
        return employees.subList(fromIndex, toIndex);
    }
    public void deleteEmployee(int id) {
        Employee employee=allEmployees.get(id);
        employee.setIsActive(false);
        allEmployees.put(id, employee);
    }
}
