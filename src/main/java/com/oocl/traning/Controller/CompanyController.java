package com.oocl.traning.Controller;

import ch.qos.logback.core.joran.sanity.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class CompanyController {
    private final static Map<Integer, Company> companies = new HashMap<>();
    private final static Map<Integer, Employee> allEmployees = new HashMap<>();

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveEmployee(Employee employee) {
        employee.setEmployeeID(allEmployees.size() + 1);
        allEmployees.put(allEmployees.size() + 1, employee);
    }

    @GetMapping("/employees/1")
    public Employee getEmployeeById(int id) {
        List<Employee> employees = new ArrayList<>(allEmployees.values());
        for (Employee employee : employees) {
            if (employee.getEmployeeID() == id) {
                return employee;
            }
        }
        return null;
    }

    @GetMapping("/employees?gender=male")
    public List<Employee> getAllMaleEmployees() {
        List<Employee> maleEmployees = new ArrayList<>();
        List<Employee> employees = new ArrayList<>(allEmployees.values());

        for (Employee employee : employees) {
            if (Objects.equals(employee.getGender(), "male")) {
                maleEmployees.add(employee);
            }
        }
        return maleEmployees;
    }

    @GetMapping("/employees")
    public ArrayList<Employee> getAllEmployees() {
        return new ArrayList<>(allEmployees.values());
    }

    @PutMapping("/employees/1")
    public void updateEmployeeAgeandSalary(int age, int salary) {
        Employee employee = allEmployees.get(1);
        employee.setAge(age);
        employee.setSalary(salary);
    }


    @GetMapping("/employees?page=1&pageSize=5")
    public ArrayList<Employee> getEmployeesByPage() {
        int PAGE = 1;
        int PAGE_SIZE = 5;
        ArrayList<Employee> employeesByPage = new ArrayList<>();
        int start = (PAGE - 1) * PAGE_SIZE;
        int end = Math.min(start + PAGE_SIZE, allEmployees.size());
        for (int i = start; i < end; i++) {
            employeesByPage.add(allEmployees.get(i));
        }
        return employeesByPage;
    }

    @GetMapping("/companies")
    public String getAllCompanies() {
        StringBuilder res = new StringBuilder();
        List<Company> companyList = new ArrayList<>(companies.values());
        for (Company company : companyList) {
            res.append(company.getCompanyIDAndName()).append("\n");
        }
        return res.toString();
    }

    @GetMapping("/companies/1")
    public String getOneCompany() {
        Company company = companies.get(1);
        return company.getCompanyIDAndName();
    }

    @GetMapping("/companies/1/employees")
    public List<Employee> getEmployeesByCompanyId() {
        return companies.get(1).getEmployees();
    }

    @GetMapping("/companies?page=1&pageSize=5")
    public ArrayList<Company> getCompaniesByPage() {
        int PAGE = 1;
        int PAGE_SIZE = 5;
        ArrayList<Company> companiesByPage = new ArrayList<>();
        int start = (PAGE - 1) * PAGE_SIZE;
        int end = Math.min(start + PAGE_SIZE, companies.size());
        for (int i = start; i < end; i++) {
            companiesByPage.add(companies.get(i));
        }
        return companiesByPage;
    }


    @PostMapping("/companies")
    @ResponseStatus(HttpStatus.CREATED)//返回创建成功的状态码
    public void postCompany(@RequestBody Company company) {
        company.setId(companies.size() + 1);
        companies.put(companies.size() + 1, company);
    }

    @PutMapping("/companies/1")
    public void updateCompanyName(String name) {
        Company company = companies.get(1);
        company.setName(name);
    }

    @DeleteMapping("/companies/1")
    public void deleteCompany() {
        companies.remove(1);
    }
}
