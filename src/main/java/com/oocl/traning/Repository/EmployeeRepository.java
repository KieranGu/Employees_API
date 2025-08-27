package com.oocl.traning.Repository;

import com.oocl.traning.Model.Employee;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeRepository {
    private final Map<Integer, Employee> allEmployees = new HashMap<>(Map.of(
            1, new Employee(1, "John Smith", 32, "MALE", 5000.0),
            2, new Employee(2, "Jane Johnson", 28, "FEMALE", 6000.0),
            3, new Employee(3, "David Williams", 35, "MALE", 5500.0),
            4, new Employee(4, "Emily Brown", 23, "FEMALE", 4500.0),
            5, new Employee(5, "Michael Jones", 40, "MALE", 7000.0)));


    public void save(Employee newEemployee) {
        allEmployees.put(newEemployee.getEmployeeID(), newEemployee);
    }
    public Employee findById(int id) {
        return allEmployees.get(id);
    }
}
