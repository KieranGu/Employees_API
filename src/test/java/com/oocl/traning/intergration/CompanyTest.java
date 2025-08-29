package com.oocl.traning.intergration;

import com.oocl.traning.model.Company;
import com.oocl.traning.model.Employee;
import com.oocl.traning.repository.CompanyDbRepository;
import com.oocl.traning.repository.EmployeeDbRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyTest {
    @Autowired
    private MockMvc client;

    @Autowired
    private CompanyDbRepository companyDbRepository;


    @BeforeEach
    void setUp(){
        companyDbRepository.clear();
        companyDbRepository.saveCompany(new Company( "Acme Corporation", List.of(
                new Employee(1, "John Smith", 32, "MALE", 5000.0),
                new Employee(2, "Jane Johnson", 28, "FEMALE", 6000.0))));
        companyDbRepository.saveCompany(new Company( "TechCom Solutions", List.of(
                new Employee(3, "David Williams", 35, "MALE", 5500.0),
                new Employee(4, "Emily Brown", 23, "FEMALE", 4500.0),
                new Employee(5, "Michael Jones", 40, "MALE", 7000.0))));
        companyDbRepository.saveCompany(new Company( "Global Innovators"));
        companyDbRepository.saveCompany(new Company( "Stellar Enterprises"));
        companyDbRepository.saveCompany(new Company( "Nexus Industries"));

    }

    @Test
    public void should_return_companies() throws Exception {
        //Given
        List<Company> givenCompanies= companyDbRepository.getAllCompanies();
        //When
        ResultActions perform= client.perform(MockMvcRequestBuilders.get("/api/v1/companies"));
        //Then
        perform.andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk());
        perform.andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.[0].id").value(givenCompanies.get(0).getId()));
    }
}
