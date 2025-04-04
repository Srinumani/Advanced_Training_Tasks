package com.wipro.Company_service.repository;

import com.wipro.Company_service.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Companyrepository extends JpaRepository<Company, Long>{
    Optional<Company> findByPcode(Long pcode);
}
