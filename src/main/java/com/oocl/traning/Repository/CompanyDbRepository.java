package com.oocl.traning.Repository;

import com.oocl.traning.Model.Company;
import com.oocl.traning.Model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyDbRepository implements CompanyRepository {
    private final JPACompanyRepository jpaCompanyRepository;
    public CompanyDbRepository(JPACompanyRepository jpaCompanyRepository) {
        this.jpaCompanyRepository = jpaCompanyRepository;
    }

    @Override
    public void saveCompany(Company company) {
        jpaCompanyRepository.save(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return jpaCompanyRepository.findAll();
    }

    @Override
    public String getAllCompaniesWithoutEmployee() {
        List<Company> companies=jpaCompanyRepository.findAll();
        StringBuilder result=new StringBuilder();
        for(Company company:companies){
            result.append("Company ID: ").append(company.getId())
                    .append(", Company Name: ").append(company.getName())
                    .append("\n");
        }
        return result.toString();
    }

    @Override
    public String getCompanyById(int id) {
        return jpaCompanyRepository.findById(id).orElse(null).getCompanyIDAndName();
    }

    @Override
    public List<Employee> getCompanyEmployeesById(int id) {
        return jpaCompanyRepository.findById(id).orElse(null).getEmployees();
    }

    @Override
    public ArrayList<Company> getCompaniesByPage(int page, int pageSize) {
        ArrayList<Company> companyList = new ArrayList<>(jpaCompanyRepository.findAll());
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, companyList.size());
        if (start >= companyList.size() || start < 0) {
            return new ArrayList<>();
        }
        return new ArrayList<>(companyList.subList(start, end));
    }

    @Override
    public void updateCompanyName(int id, Company company) {
        Company targetCompany=jpaCompanyRepository.findById(id).orElse(null);
        if(targetCompany!=null){
            targetCompany.setName(company.getName());
            jpaCompanyRepository.save(targetCompany);
        }
    }

    @Override
    public void deleteCompany(int id) {
        jpaCompanyRepository.deleteById(id);
    }
}
