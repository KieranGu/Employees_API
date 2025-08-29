package com.oocl.traning.service;

import com.oocl.traning.model.Company;
import com.oocl.traning.model.Employee;
import com.oocl.traning.repository.CompanyDbRepository;
import com.oocl.traning.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyDbRepository;


    public CompanyService(CompanyDbRepository companyDbRepository) {
        this.companyDbRepository = companyDbRepository;
    }

    public void saveCompany(Company company) {
        companyDbRepository.saveCompany(company);
    }

    public String getAllCompaniesWithoutEmployees() {
        return companyDbRepository.getAllCompaniesWithoutEmployee();
    }

    public String getCompanyById(int id) {
        return companyDbRepository.getCompanyById(id);
    }

    public List<Employee> getCompanyEmployeesById(int id) {
        return companyDbRepository.getCompanyEmployeesById(id);
    }

    public ArrayList<Company> getCompaniesByPage(int page, int pageSize) {
        return companyDbRepository.getCompaniesByPage(page, pageSize);
    }

    public void updateCompanyName(int id, Company company){
        if(company.getId()==id) companyDbRepository.updateCompanyName(id,company);
    }

    public void deleteCompany(int id){
        companyDbRepository.deleteCompany(id);
    }

    public List<Company> getAllCompanies() {
        return companyDbRepository.getAllCompanies();
    }
}
