package com.jb.couponSystemPhaseTwo.controller;

import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import com.jb.couponSystemPhaseTwo.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
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
    @PostMapping("companies")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestBody Company company) throws SQLException, CouponSystemException {
        adminService.addCompany(company);
    }

    @Override
    @PutMapping("companies/company/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCompany(@PathVariable int companyId,@RequestBody Company company) throws SQLException, CouponSystemException {
        adminService.updateCompany(companyId, company);
    }

    @Override
    @DeleteMapping("companies/company/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable int companyId) throws SQLException, CouponSystemException {
        adminService.deleteCompany(companyId);
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
    @PostMapping("customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer customer) throws SQLException, CouponSystemException {
        adminService.addCustomer(customer);
    }

    @Override
    @PutMapping("customers/customer/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable int customerId, @RequestBody Customer customer) throws SQLException, CouponSystemException {
        adminService.updateCustomer(customerId, customer);
    }

    @Override
    @DeleteMapping("customers/customer/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int customerId) throws SQLException, CouponSystemException {
        adminService.deleteCustomer(customerId);
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
