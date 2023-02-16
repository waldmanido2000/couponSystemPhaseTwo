package com.jb.couponSystemPhaseTwo.controller;

import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSecurityException;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import com.jb.couponSystemPhaseTwo.exceptions.SecurityMessage;
import com.jb.couponSystemPhaseTwo.services.AdminService;
import com.jb.couponSystemPhaseTwo.services.ClientType;
import com.jb.couponSystemPhaseTwo.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class AdminControllerImpl implements AdminController {
    @Autowired
    @Qualifier("adminServiceImpl")
    private AdminService adminService;
    @Autowired
    private TokenService tokenService;

    @Override
    @PostMapping("companies")
    @ResponseStatus(HttpStatus.CREATED)
    public Company addCompany(@RequestBody Company company) throws SQLException, CouponSystemException {
        adminService.addCompany(company);
        return company;
    }

    @Override
    @PutMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Company updateCompany(@PathVariable int companyId, @RequestBody Company company) throws SQLException, CouponSystemException {
        adminService.updateCompany(companyId, company);
        return company;
    }

    @Override
    @DeleteMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable int companyId) throws SQLException, CouponSystemException {
        adminService.deleteCompany(companyId);
    }

    @Override
    @GetMapping("companies")
    public List<Company> getAllCompanies(@RequestHeader("Authorization") UUID token) throws SQLException, CouponSecurityException {
        if (!tokenService.isValid(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        return adminService.getAllCompanies();
    }

    @Override
    @GetMapping("companies/{id}")
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
    @PutMapping("customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable int customerId, @RequestBody Customer customer) throws SQLException, CouponSystemException {
        adminService.updateCustomer(customerId, customer);
    }

    @Override
    @DeleteMapping("customers/{customerId}")
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
    @GetMapping("customers/{id}")
    public Customer getOneCustomer(@PathVariable("id") int customerId) throws SQLException, CouponSystemException {
        return adminService.getOneCustomer(customerId);
    }
}
