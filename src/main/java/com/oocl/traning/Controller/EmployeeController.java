package com.oocl.traning.Controller;

import com.oocl.traning.Model.Company;
import com.oocl.traning.Model.Employee;
import com.oocl.traning.Repository.EmployeeRepository;
import com.oocl.traning.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
//    private final static Map<Integer, Company> companies = new HashMap<>();
//    private final static Map<Integer, Employee> allEmployees = new HashMap<>();
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    private final Map<Integer, Employee> allEmployees = new HashMap<>(Map.of(
//            1, new Employee(1, "John Smith", 32, "MALE", 5000.0),
//            2, new Employee(2, "Jane Johnson", 28, "FEMALE", 6000.0),
//            3, new Employee(3, "David Williams", 35, "MALE", 5500.0),
//            4, new Employee(4, "Emily Brown", 23, "FEMALE", 4500.0),
//            5, new Employee(5, "Michael Jones", 40, "MALE", 7000.0)));

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveEmployee(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(value = "/employees",params = {"gender"})
    public List<Employee> getAllEmployeesByGender(@RequestParam(required = false) String gender) {
        return employeeService.getEmployeesByGender(gender);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PutMapping("/employees/{id}")
    public void updateEmployeeAgeandSalary(@PathVariable int id, @RequestBody int salary,int age) {
        employeeService.updateEmployeeAgeandSalary(id, age, salary);
    }


    @GetMapping(value = "/employees",params = {"page","pageSize"})
    public List<Employee> getEmployeesByPage(@RequestBody int page,int pageSize) {
        return employeeService.getEmployeesByPage(page, pageSize);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }

}
