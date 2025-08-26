package com.oocl.traning.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CompanyController {
    private final static Map<Integer,Company> companies = new HashMap<>();
    private final static ArrayList<Employee> allEmployees = new ArrayList<>();
    @PostMapping("/employees")
    public void postEmployee(Employee employee){
        employee.setEmployeeID(allEmployees.size()+1);
        allEmployees.set(allEmployees.size()+1,employee);
    }

    @GetMapping("/employees")
    public ArrayList<Employee> getEmployees(){
        return allEmployees;
    }




    @PostMapping("/companies")
    @ResponseStatus(HttpStatus.CREATED)//返回创建成功的状态码
    public void postCompany(@RequestBody Company company){
        company.setId(companies.size()+1);
        companies.put(companies.size()+1,company);
    }

}
