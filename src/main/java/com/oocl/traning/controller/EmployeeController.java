package com.oocl.traning.controller;

import com.oocl.traning.controller.dto.EmployeeRequest;
import com.oocl.traning.controller.dto.EmployeeResponse;
import com.oocl.traning.controller.mapper.EmployeeMapper;
import com.oocl.traning.model.Employee;
import com.oocl.traning.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService,EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper=employeeMapper;
    }



//    @PostMapping("/employees")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void saveEmployee(@RequestBody Employee employee) {
//        employeeService.createEmployee(employee);
//    }
    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse saveEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        Employee employee=employeeMapper.toEntity(employeeRequest);
        return employeeMapper.toResponse(employeeService.createEmployee(employee));//修改为请求体
    }


    @GetMapping("/employees/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable Integer id) {
        return employeeMapper.toResponse(employeeService.getEmployeeById(id));
    }

    @GetMapping(value = "/employees",params = {"gender"})
    public List<EmployeeResponse> getAllEmployeesByGender(@RequestParam(required = false) String gender) {
        return employeeMapper.toResponse(employeeService.getEmployeesByGender(gender));
    }

    @GetMapping("/employees")
    public List<EmployeeResponse> getAllEmployees() {
        return employeeMapper.toResponse(employeeService.getAllEmployees());
    }

    @PutMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployeeAgeandSalary(@PathVariable Integer id, @RequestBody Double salary,Integer age) {
        employeeService.updateEmployeeAgeandSalary(id, age, salary);
    }


    @GetMapping(value = "/employees",params = {"page","pageSize"})
    public List<EmployeeResponse> getEmployeesByPage(@RequestBody Integer page,Integer pageSize) {
        return employeeMapper.toResponse(employeeService.getEmployeesByPage(page, pageSize));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }

}
