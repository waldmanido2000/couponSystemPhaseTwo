package com.jb.couponSystemPhaseTwo.controller;

import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import com.jb.couponSystemPhaseTwo.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api")
public class AdminControllerImpl implements AdminService {
    @Autowired
    @Qualifier("adminServiceImpl")
    private AdminService adminService;


    @Override
    public void addCompany(Company company) throws SQLException, CouponSystemException {

    }

    @Override
    public void updateCompany(int companyId, Company company) throws SQLException, CouponSystemException {

    }

    @Override
    public void deleteCompany(int companyId) throws SQLException, CouponSystemException {

    }

    @Override
    @GetMapping
    public List<Company> getAllCompanies() throws SQLException {
        return adminService.getAllCompanies();
    }

    @Override
    public Company getOneCompany(int companyId) throws SQLException, CouponSystemException {
        return null;
    }

    @Override
    public void addCustomer(Customer customer) throws SQLException, CouponSystemException {

    }

    @Override
    public void updateCustomer(int customerId, Customer customer) throws SQLException, CouponSystemException {

    }

    @Override
    public void deleteCustomer(int customerId) throws SQLException, CouponSystemException {

    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        return null;
    }

    @Override
    public Customer getOneCustomer(int customerId) throws SQLException, CouponSystemException {
        return null;
    }
}
