package com.oocl.traning.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class CompanyController {
    private final static Map<Integer, Company> companies = new HashMap<>();
    private final static ArrayList<Employee> allEmployees = new ArrayList<>();

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public void postEmployee(Employee employee) {
        employee.setEmployeeID(allEmployees.size() + 1);
        allEmployees.set(allEmployees.size() + 1, employee);
    }

    @GetMapping("/employees/1")
    public Employee getEmployeeById(int id) {
        for (Employee employee : allEmployees) {
            if (employee.getEmployeeID() == id) {
                return employee;
            }
        }
        return null;
    }

    @GetMapping("/employees?gender=male")
    public ArrayList<Employee> getAllMaleEmployees(){
        ArrayList<Employee> maleEmployees = new ArrayList<>();
        for (Employee employee : allEmployees) {
            if (Objects.equals(employee.getGender(), "male")) {
                maleEmployees.add(employee);
            }
        }
        return maleEmployees;
    }

    @GetMapping("/employees")
    public ArrayList<Employee> getAllEmployees() {
        return allEmployees;
    }

    @PutMapping("/employees/1")
    public void updateEmployeeAgeandSalary(int id, int age, int salary) {
        for (Employee employee : allEmployees) {
            if (employee.getEmployeeID() == id) {
                employee.setAge(age);
                employee.setSalary(salary);
            }
        }
    }


    @PostMapping("/companies")
    @ResponseStatus(HttpStatus.CREATED)//返回创建成功的状态码
    public void postCompany(@RequestBody Company company) {
        company.setId(companies.size() + 1);
        companies.put(companies.size() + 1, company);
    }

}
