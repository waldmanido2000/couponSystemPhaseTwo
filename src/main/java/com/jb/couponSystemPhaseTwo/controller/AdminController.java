package com.jb.couponSystemPhaseTwo.controller;

import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSecurityException;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
public interface AdminController {
    Company addCompany(Company company) throws SQLException, CouponSystemException;

    Company updateCompany(int companyId, Company company) throws SQLException, CouponSystemException;

    void deleteCompany(int companyId) throws SQLException, CouponSystemException;

    List<Company> getAllCompanies(UUID token) throws SQLException, CouponSecurityException;

    Company getOneCompany(int companyId) throws SQLException, CouponSystemException;

    // customers functions
    void addCustomer(Customer customer) throws SQLException, CouponSystemException;

    void updateCustomer(int customerId, Customer customer) throws SQLException, CouponSystemException;

    void deleteCustomer(int customerId) throws SQLException, CouponSystemException;

    List<Customer> getAllCustomers() throws SQLException;

    Customer getOneCustomer(int customerId) throws SQLException, CouponSystemException;
}
