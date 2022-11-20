package com.jb.couponSystemPhaseTwo.services;

import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public interface AdminService {
    // companies functions
    void addCompany(Company company) throws SQLException, CouponSystemException;

    void updateCompany(int companyId, Company company) throws SQLException, CouponSystemException;

    void deleteCompany(int companyId) throws SQLException, CouponSystemException;

    List<Company> getAllCompanies() throws SQLException;

    Company getOneCompany(int companyId) throws SQLException, CouponSystemException;

    // customers functions
    void addCustomer(Customer customer) throws SQLException, CouponSystemException;

    void updateCustomer(int customerId, Customer customer) throws SQLException, CouponSystemException;

    void deleteCustomer(int customerId) throws SQLException, CouponSystemException;

    List<Customer> getAllCustomers() throws SQLException;

    Customer getOneCustomer(int customerId) throws SQLException, CouponSystemException;
}
