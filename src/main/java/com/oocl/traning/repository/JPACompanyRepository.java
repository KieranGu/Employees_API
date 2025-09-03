package com.oocl.traning.repository;

import com.oocl.traning.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPACompanyRepository extends JpaRepository<Company, Integer> {

}
