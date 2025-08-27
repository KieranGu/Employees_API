package com.oocl.traning.Repository;

import com.oocl.traning.Model.Company;
import com.oocl.traning.Model.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CompanyRepository {
    private final Map<Integer, Company> companies = new HashMap<>(Map.of(
            1, new Company(1, "Acme Corporation", List.of(
                    new Employee(1, "John Smith", 32, "MALE", 5000.0),
                    new Employee(2, "Jane Johnson", 28, "FEMALE", 6000.0)
            )),
            2, new Company(2, "TechCom Solutions", List.of(
                    new Employee(3, "David Williams", 35, "MALE", 5500.0),
                    new Employee(4, "Emily Brown", 23, "FEMALE", 4500.0),
                    new Employee(5, "Michael Jones", 40, "MALE", 7000.0)
            )),
            3, new Company(3, "Global Innovators"),
            4, new Company(4, "Stellar Enterprises"),
            5, new Company(5, "Nexus Industries")
    ));

    public void saveCompany(Company company) {
        int id= Collections.max(companies.keySet())+1;
        company.setId(id);
        companies.put(company.getId(), company);
    }

    public List<Company> getAllCompanies() {
        return List.copyOf(companies.values());
    }

    public String getAllCompaniesWithoutEmployee(){
        StringBuilder result = new StringBuilder();
        for (Company company : companies.values()) {
            result.append("Company ID: ").append(company.getId())
                  .append(", Name: ").append(company.getName())
                  .append("\n");
        }
        return result.toString();
    }

    public String getCompanyById(int id) {
        Company targetCompany= companies.get(id);
        return "Company ID: " + targetCompany.getId() +
                ", Name: " + targetCompany.getName();
    }

    public List<Employee> getCompanyEmployeesById(int id) {
        Company targetCompany= companies.get(id);
        return targetCompany.getEmployees();
    }

    public ArrayList<Company> getCompaniesByPage(int page, int pageSize) {
        ArrayList<Company> companyList = new ArrayList<>(companies.values());
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, companyList.size());
        if (start >= companyList.size() || start < 0) {
            return new ArrayList<>();
        }
        return new ArrayList<>(companyList.subList(start, end));
    }

    public void updateCompanyName(int id, Company company) {
        companies.put(id,company);
    }

    public void deleteCompany(int id) {
        companies.remove(id);
    }
}
