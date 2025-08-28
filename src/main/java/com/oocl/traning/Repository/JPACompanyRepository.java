package com.oocl.traning.Repository;

import com.oocl.traning.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPACompanyRepository extends JpaRepository<Company, Integer> {

}
