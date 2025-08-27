package com.oocl.traning.Service;

import com.oocl.traning.Model.Company;
import com.oocl.traning.Model.Employee;
import com.oocl.traning.Repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void saveCompany(Company company) {
        companyRepository.saveCompany(company);
    }

    public String getAllCompaniesWithoutEmployees() {
        return companyRepository.getAllCompaniesWithoutEmployee();
    }

    public String getCompanyById(int id) {
        return companyRepository.getCompanyById(id);
    }

    public List<Employee> getCompanyEmployeesById(int id) {
        return companyRepository.getCompanyEmployeesById(id);
    }

    public ArrayList<Company> getCompaniesByPage(int page, int pageSize) {
        return companyRepository.getCompaniesByPage(page, pageSize);
    }

    public void updateCompanyName(int id, Company company){
        if(company.getId()==id) companyRepository.updateCompanyName(id,company);
    }

    public void deleteCompany(int id){
        companyRepository.deleteCompany(id);
    }
}
