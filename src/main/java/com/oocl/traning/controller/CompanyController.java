package com.oocl.traning.controller;

import com.oocl.traning.model.Company;
import com.oocl.traning.model.Employee;
import com.oocl.traning.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CompanyController {
    private CompanyService companyService;
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(value = "/companies",params = {})
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/companies/{id}")
    public String getOneCompany(@PathVariable int id) {
        return companyService.getCompanyById(id);
    }

    @GetMapping("/companies/{id}/employees")
    public List<Employee> getEmployeesByCompanyId(@PathVariable int id) {
        return companyService.getCompanyEmployeesById(id);
    }

    @GetMapping(value = "/companies",params = {"page","pageSize"})
    public ArrayList<Company> getCompaniesByPage(@RequestBody int page,int pageSize) {
        return companyService.getCompaniesByPage(page,pageSize);
    }


    @PostMapping("/companies")
    @ResponseStatus(HttpStatus.CREATED)//返回创建成功的状态码
    public void postCompany(@RequestBody Company company) {
        companyService.saveCompany(company);
    }

    @PutMapping("/companies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCompanyName(@PathVariable int id, @RequestBody Company company) {
        companyService.updateCompanyName(id,company);
    }

    @DeleteMapping("/companies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable int id) {
        companyService.deleteCompany(id);
    }
}
