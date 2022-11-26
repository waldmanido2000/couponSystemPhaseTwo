package com.jb.couponSystemPhaseTwo.controller;

import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import com.jb.couponSystemPhaseTwo.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api")
public class AdminControllerImpl implements AdminService {
    @Autowired
    @Qualifier("adminServiceImpl")
    private AdminService adminService;

    @Override
    @PostMapping("companies/add")
    public void addCompany(@RequestBody Company company) throws SQLException, CouponSystemException {
        adminService.addCompany(company);
    }

    @Override
    public void updateCompany(int companyId, Company company) throws SQLException, CouponSystemException {

    }

    @Override
    public void deleteCompany(int companyId) throws SQLException, CouponSystemException {

    }

    @Override
    @GetMapping("companies")
    public List<Company> getAllCompanies() throws SQLException {
        return adminService.getAllCompanies();
    }

    @Override
    @GetMapping("companies/company/{id}")
    public Company getOneCompany(@PathVariable("id") int companyId) throws SQLException, CouponSystemException {
        return adminService.getOneCompany(companyId);
    }

    @Override
    @PostMapping("customers/add")
    public void addCustomer(@RequestBody Customer customer) throws SQLException, CouponSystemException {
        adminService.addCustomer(customer);
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) throws SQLException, CouponSystemException {

    }

    @Override
    public void deleteCustomer(int customerId) throws SQLException, CouponSystemException {

    }

    @Override
    @GetMapping("customers")
    public List<Customer> getAllCustomers() throws SQLException {
        return adminService.getAllCustomers();
    }

    @Override
    @GetMapping("customers/customer/{id}")
    public Customer getOneCustomer(@PathVariable("id") int customerId) throws SQLException, CouponSystemException {
        return adminService.getOneCustomer(customerId);
    }
}
