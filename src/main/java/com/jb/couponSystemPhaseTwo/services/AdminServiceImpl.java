package com.jb.couponSystemPhaseTwo.services;

import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public class AdminServiceImpl  extends ClientService implements AdminService{
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
    public List<Company> getAllCompanies() throws SQLException {
        return null;
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

    @Override
    public boolean login(String email, String password) throws SQLException, CouponSystemException {
        return false;
    }
}
