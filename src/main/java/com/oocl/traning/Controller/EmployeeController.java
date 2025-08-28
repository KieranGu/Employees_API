package com.oocl.traning.Controller;

import com.oocl.traning.Model.Employee;
import com.oocl.traning.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }



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
    public void updateEmployeeAgeandSalary(@PathVariable int id, @RequestBody double salary,int age) {
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
