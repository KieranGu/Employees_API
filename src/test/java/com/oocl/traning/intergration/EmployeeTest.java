package com.oocl.traning.intergration;

import com.oocl.traning.controller.dto.EmployeeResponse;
import com.oocl.traning.model.Employee;
import com.oocl.traning.repository.EmployeeDbRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import com.oocl.traning.controller.mapper.EmployeeMapper;

import org.springframework.http.MediaType;
@Disabled
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeTest {
    @Autowired
    private MockMvc client;

    @Autowired
    private EmployeeDbRepository employeeDbRepository;
    private final EmployeeMapper employeeMapper=new EmployeeMapper();

    @BeforeEach
    void setUp(){
        employeeDbRepository.clear();
        employeeDbRepository.save(new Employee("John Smith", 32, "MALE", 5000.0));
        employeeDbRepository.save(new Employee( "Jane Johnson", 28, "FEMALE", 6000.0));
        employeeDbRepository.save(new Employee( "David Williams", 35, "MALE", 5500.0));
        employeeDbRepository.save(new Employee( "Emily Brown", 23, "FEMALE", 4500.0));
        employeeDbRepository.save(new Employee( "Michael Jones", 40, "MALE", 7000.0));

    }

    @Test
    public void should_return_employees_when_get_all_employees() throws Exception {
        //Given
        List<Employee> givenEmployees= employeeDbRepository.findAll();
        //When
        ResultActions perform= client.perform(MockMvcRequestBuilders.get("/api/v1/employees"));
        //Then
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].employeeId").value(givenEmployees.get(0).getEmployeeId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].employeeName").value(givenEmployees.get(0).getEmployeeName()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].age").value(givenEmployees.get(0).getAge()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].gender").value(givenEmployees.get(0).getGender()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].salary").value(givenEmployees.get(0).getSalary()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[1].employeeId").value(givenEmployees.get(1).getEmployeeId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[2].employeeId").value(givenEmployees.get(2).getEmployeeId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[3].employeeId").value(givenEmployees.get(3).getEmployeeId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[4].employeeId").value(givenEmployees.get(4).getEmployeeId()));

    }

    @Test
    public void should_return_a_empoloyee_when_find_by_id() throws Exception{
        //Given
        Employee givenEmployees= employeeDbRepository.findById(101);
        //When
        ResultActions perform= client.perform(MockMvcRequestBuilders.get("/api/v1/employees/101"));
        //Then
        perform.andExpect(MockMvcResultMatchers.status().isOk());

        perform.andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").value(givenEmployees.getEmployeeId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.employeeName").value(givenEmployees.getEmployeeName()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.age").value(givenEmployees.getAge()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.salary").value(givenEmployees.getSalary()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.isActive").value(givenEmployees.getIsActive()));
    }

    @Test
    public void should_return_a_empoloyee_when_find_by_gender() throws Exception{
        //Given
        List<Employee> givenEmployees= employeeDbRepository.findGender("MALE");
        //When
        ResultActions perform= client.perform(MockMvcRequestBuilders.get("/api/v1/employees?gender=MALE"));
        //Then
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].employeeId").value(givenEmployees.get(0).getEmployeeId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].employeeName").value(givenEmployees.get(0).getEmployeeName()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].gender").value("MALE"));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].age").value(givenEmployees.get(0).getAge()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].salary").value(givenEmployees.get(0).getSalary()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].isActive").value(givenEmployees.get(0).getIsActive()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[1].employeeId").value(givenEmployees.get(1).getEmployeeId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[1].gender").value("MALE"));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[2].employeeId").value(givenEmployees.get(2).getEmployeeId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[2].gender").value("MALE"));
    }

    @Test
    public void should_throw_excepition_when_invalid_employee() throws Exception{
        //Given
        String jsonBody="{ \"salary\": 10000.0,\"age\": 15  }";
        //When
        ResultActions perform= client.perform(MockMvcRequestBuilders.put("/api/v1/employees/1")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON));
        //Then
        perform.andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

}
