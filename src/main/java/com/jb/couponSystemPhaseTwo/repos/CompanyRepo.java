package com.jb.couponSystemPhaseTwo.repos;

import com.jb.couponSystemPhaseTwo.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository <Company, Integer> {
    boolean existsByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
    boolean existsByName(String name);
}
