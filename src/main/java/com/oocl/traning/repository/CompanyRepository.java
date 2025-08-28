package com.oocl.traning.repository;

import com.oocl.traning.model.Company;
import com.oocl.traning.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface CompanyRepository {


    void saveCompany(Company company);

    List<Company> getAllCompanies();

    String getAllCompaniesWithoutEmployee();
    String getCompanyById(int id) ;

    List<Employee> getCompanyEmployeesById(int id);

    ArrayList<Company> getCompaniesByPage(int page, int pageSize) ;
    void updateCompanyName(int id, Company company);

    void deleteCompany(int id);
}
