package com.jb.couponSystemPhaseTwo.controller;

import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface AdminController {
    void addCompany(Company company);

    void updateCompany(int companyId, Company company);

    void deleteCompany(int companyId);

    List<Company> getAllCompanies();

    Company getOneCompany(int companyId);

    // customers functions
    void addCustomer(Customer customer);

    void updateCustomer(int customerId, Customer customer);

    void deleteCustomer(int customerId);

    List<Customer> getAllCustomers();

    Customer getOneCustomer(int customerId);
}
