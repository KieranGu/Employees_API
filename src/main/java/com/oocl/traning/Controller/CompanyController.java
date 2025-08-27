package com.oocl.traning.Controller;

import ch.qos.logback.core.joran.sanity.Pair;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class CompanyController {
//    private final static Map<Integer, Company> companies = new HashMap<>();
    private final HashMap<Integer, Company> companies = new HashMap<>(Map.of(
            1, new Company(1, "Acme Corporation", List.of(
                    new Employee(1, "John Smith", 32, "MALE", 5000.0),
                    new Employee(2, "Jane Johnson", 28, "FEMALE", 6000.0)
            )),
            2, new Company(2, "TechCom Solutions", List.of(
                    new Employee(3, "David Williams", 35, "MALE", 5500.0),
                    new Employee(4, "Emily Brown", 23, "FEMALE", 4500.0),
                    new Employee(5, "Michael Jones", 40, "MALE", 7000.0)
            )),
            3, new Company(3, "Global Innovators"),
            4, new Company(4, "Stellar Enterprises"),
            5, new Company(5, "Nexus Industries")
    ));
//    private final static Map<Integer, Employee> allEmployees = new HashMap<>();

    private final Map<Integer, Employee> allEmployees = new HashMap<>(Map.of(
            1, new Employee(1, "John Smith", 32, "MALE", 5000.0),
            2, new Employee(2, "Jane Johnson", 28, "FEMALE", 6000.0),
            3, new Employee(3, "David Williams", 35, "MALE", 5500.0),
            4, new Employee(4, "Emily Brown", 23, "FEMALE", 4500.0),
            5, new Employee(5, "Michael Jones", 40, "MALE", 7000.0)));

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveEmployee(@RequestBody Employee employee) {
        employee.setEmployeeID(allEmployees.size() + 1);
        allEmployees.put(allEmployees.size() + 1, employee);
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        List<Employee> employees = new ArrayList<>(allEmployees.values());
        for (Employee employee : employees) {
            if (employee.getEmployeeID() == id) {
                return employee;
            }
        }
        return null;
    }

    @GetMapping("/employees")
    public ArrayList<Employee> getAllMaleEmployees(@RequestParam(required = false) String gender) {
        if (gender == null || gender.isEmpty()) {
            return (ArrayList<Employee>) allEmployees.values(); // 返回所有员工
        }
        List<Employee> maleEmployees = new ArrayList<>();
        List<Employee> employees = new ArrayList<>(allEmployees.values());

        for (Employee employee : employees) {
            if (Objects.equals(employee.getGender(), gender)) {
                maleEmployees.add(employee);
            }
        }
        return (ArrayList<Employee>) maleEmployees;
    }

    @GetMapping("/employees")
    public ArrayList<Employee> getAllEmployees() {
        return new ArrayList<>(allEmployees.values());
    }

    @PutMapping("/employees/{id}")
    public void updateEmployeeAgeandSalary(@PathVariable int id, @RequestBody int salary,int age) {
        Employee employee = allEmployees.get(id);
        employee.setAge(age);
        employee.setSalary(salary);
    }


    @GetMapping("/employees")
    public ArrayList<Employee> getEmployeesByPage(@RequestBody int page,int pageSize) {
        ArrayList<Employee> employeesByPage = new ArrayList<>();
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, allEmployees.size());
        for (int i = start; i < end; i++) {
            employeesByPage.add(allEmployees.get(i));
        }
        return employeesByPage;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id) {
        allEmployees.remove(id);
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

    @GetMapping("/companies/{id}")
    public String getOneCompany(@PathVariable int id) {
        Company company = companies.get(id);
        return company.getCompanyIDAndName();
    }

    @GetMapping("/companies/{id}/employees")
    public List<Employee> getEmployeesByCompanyId(@PathVariable int id) {
        return companies.get(id).getEmployees();
    }

    @GetMapping("/companies")
    public ArrayList<Company> getCompaniesByPage(@RequestBody int page,int pageSize) {
        ArrayList<Company> companiesByPage = new ArrayList<>();
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, companies.size());
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

    @PutMapping("/companies/{id}")
    public void updateCompanyName(@PathVariable int id, @RequestBody Company company) {
        companies.put(id, company);
    }

    @DeleteMapping("/companies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable int id) {
        companies.remove(id);
    }
}
