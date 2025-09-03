package com.oocl.traning.controller.mapper;

import com.oocl.traning.controller.dto.EmployeeRequest;
import com.oocl.traning.controller.dto.EmployeeResponse;
import com.oocl.traning.model.Company;
import com.oocl.traning.model.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

import java.util.List;

@Component
public class EmployeeMapper {
    public EmployeeResponse toResponse(Employee employee){
        EmployeeResponse employeeResponse=new EmployeeResponse();
        BeanUtils.copyProperties(employee,employeeResponse);
        return employeeResponse;
    }
    public List<EmployeeResponse> toResponse(List<Employee> employees){
        return employees.stream().map(employee -> {
            EmployeeResponse employeeResponse = new EmployeeResponse();
            BeanUtils.copyProperties(employee, employeeResponse);
            return employeeResponse;
        }).collect(Collectors.toList());

//        return employees.stream().map(this::toResponse).toList();
    }

    public Employee toEntity(EmployeeRequest employeeRequest){
        Employee employee=new Employee();
        BeanUtils.copyProperties(employeeRequest,employee);
        employee.setCompany((new Company(employeeRequest.getCompanyId())));
        return employee;
    }
}
