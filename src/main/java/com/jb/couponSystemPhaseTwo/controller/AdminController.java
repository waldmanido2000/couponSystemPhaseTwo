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
    Company addCompany(Company company, UUID token) throws SQLException, CouponSystemException, CouponSecurityException;

    Company updateCompany(int companyId, Company company, UUID token) throws SQLException, CouponSystemException, CouponSecurityException;

    void deleteCompany(int companyId, UUID token) throws SQLException, CouponSystemException, CouponSecurityException;

    List<Company> getAllCompanies(UUID token) throws SQLException, CouponSecurityException;

    Company getOneCompany(int companyId, UUID token) throws SQLException, CouponSystemException, CouponSecurityException;

    // customers functions
    void addCustomer(Customer customer, UUID token) throws SQLException, CouponSystemException, CouponSecurityException;

    void updateCustomer(int customerId, Customer customer, UUID token) throws SQLException, CouponSystemException, CouponSecurityException;

    void deleteCustomer(int customerId, UUID token) throws SQLException, CouponSystemException, CouponSecurityException;

    List<Customer> getAllCustomers(UUID token) throws SQLException, CouponSecurityException;

    Customer getOneCustomer(int customerId, UUID token) throws SQLException, CouponSystemException, CouponSecurityException;
}
