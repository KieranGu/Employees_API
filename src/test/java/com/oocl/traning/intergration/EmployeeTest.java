package com.oocl.traning.intergration;

import com.oocl.traning.Model.Employee;
import com.oocl.traning.Repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeTest {
    @Autowired
    private MockMvc client;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp(){
        employeeRepository.clear();
        employeeRepository.save(new Employee(1, "John Smith", 32, "MALE", 5000.0));
        employeeRepository.save(new Employee(2, "Jane Johnson", 28, "FEMALE", 6000.0));
        employeeRepository.save(new Employee(3, "David Williams", 35, "MALE", 5500.0));
        employeeRepository.save(new Employee(4, "Emily Brown", 23, "FEMALE", 4500.0));
        employeeRepository.save(new Employee(5, "Michael Jones", 40, "MALE", 7000.0));

    }

    @Test
    public void should_return_employees_when_get_all_employees() throws Exception {
        //Given
        List<Employee> givenEmployees=employeeRepository.findAll();
        //When
        ResultActions perform= client.perform(MockMvcRequestBuilders.get("/api/v1/employees"));
        //Then
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].employeeID").value(givenEmployees.get(0).getEmployeeID()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].employeeName").value(givenEmployees.get(0).getEmployeeName()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].age").value(givenEmployees.get(0).getAge()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].gender").value(givenEmployees.get(0).getGender()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].salary").value(givenEmployees.get(0).getSalary()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[1].employeeID").value(givenEmployees.get(1).getEmployeeID()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[2].employeeID").value(givenEmployees.get(2).getEmployeeID()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[3].employeeID").value(givenEmployees.get(3).getEmployeeID()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[4].employeeID").value(givenEmployees.get(4).getEmployeeID()));

    }

    @Test
    public void should_return_a_empoloyee_when_find_by_id() throws Exception{
        //Given
        Employee givenEmployees=employeeRepository.findById(2);
        //When
        ResultActions perform= client.perform(MockMvcRequestBuilders.get("/api/v1/employees/2"));
        //Then
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.employeeID").value(givenEmployees.getEmployeeID()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.employeeName").value(givenEmployees.getEmployeeName()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.age").value(givenEmployees.getAge()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.salary").value(givenEmployees.getSalary()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.isActive").value(givenEmployees.getIsActive()));
    }


}
