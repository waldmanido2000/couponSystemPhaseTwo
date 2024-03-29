package com.jb.couponSystemPhaseTwo.services;

import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.dto.LoginResDto;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSecurityException;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import com.jb.couponSystemPhaseTwo.exceptions.ErrorMessage;
import com.jb.couponSystemPhaseTwo.exceptions.SecurityMessage;
import com.jb.couponSystemPhaseTwo.security.LoginInfo;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class AdminServiceImpl extends ClientService implements AdminService {
    @Override
    public Company addCompany(Company company) throws CouponSystemException {
        if (companyRepo.existsByEmail(company.getEmail())) {
            throw new CouponSystemException(ErrorMessage.COMPANY_EXISTS_BY_EMAIL);
        }
        if (companyRepo.existsByName(company.getName())) {
            throw new CouponSystemException(ErrorMessage.COMPANY_EXISTS_BY_NAME);
        }
        companyRepo.save(company);
        return company;
    }

    @Override
    public Company updateCompany(int companyId, Company company) throws SQLException, CouponSystemException {
        company.setId(companyId);
        Company companyFromDB = companyRepo.findById(companyId)
                .orElseThrow(() -> new CouponSystemException(ErrorMessage.COMPANY_NOT_EXIST));
        if (!Objects.equals(company.getName(), companyFromDB.getName())) {
            throw new CouponSystemException(ErrorMessage.CANT_CHANGE_COMPANY_NAME);
        }
        for (Coupon coupon : company.getCoupons()
        ) {
            coupon.setCompany(company);
        }
        companyRepo.saveAndFlush(company);
        return company;
    }

    @Override
    public void deleteCompany(int companyId) throws SQLException, CouponSystemException {
        if (!companyRepo.existsById(companyId)) {
            throw new CouponSystemException(ErrorMessage.COMPANY_NOT_EXIST);
        }
        couponRepo.deletePurchasesByCompany(companyId);
        companyRepo.deleteById(companyId);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public Company getOneCompany(int companyId) throws CouponSystemException {
        return companyRepo.findById(companyId).orElseThrow(() -> new CouponSystemException(ErrorMessage.COMPANY_NOT_EXIST));
    }

    @Override
    public void addCustomer(Customer customer) throws SQLException, CouponSystemException {
        if (customerRepo.existsByEmail(customer.getEmail())) {
            throw new CouponSystemException(ErrorMessage.CUSTOMER_EXISTS_BY_EMAIL);
        }
        customerRepo.save(customer);
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) throws CouponSystemException {
        if (!customerRepo.existsById(customerId)) {
            throw new CouponSystemException(ErrorMessage.CUSTOMER_NOT_EXIST);
        }
        customer.setId(customerId);
        customerRepo.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(int customerId) throws CouponSystemException {
        if (!customerRepo.existsById(customerId)) {
            throw new CouponSystemException(ErrorMessage.CUSTOMER_NOT_EXIST);
        }
        couponRepo.deletePurchasesByCustomer(customerId);
        customerRepo.deleteById(customerId);
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        return customerRepo.findAll();
    }

    @Override
    public Customer getOneCustomer(int customerId) throws CouponSystemException {
        return customerRepo.findById(customerId)
                .orElseThrow(() -> new CouponSystemException(ErrorMessage.CUSTOMER_NOT_EXIST));
    }

    @Override
    public LoginResDto login(String email, String password) throws CouponSecurityException {
        boolean res = Objects.equals(email, "admin@admin.com") && Objects.equals(password, "admin");
        if (res) {
            int adminId = 0;
            tokenService.addClient(adminId, ClientType.ADMINISTRATOR);
            LoginInfo loginInfo = LoginInfo.builder()
                    .id(adminId)
                    .clientType(ClientType.ADMINISTRATOR)
                    .time(LocalDateTime.now())
                    .build();
            UUID token = tokenService.getToken(loginInfo);
            return LoginResDto.builder()
                    .id(adminId)
                    .token(token)
                    .clientType(ClientType.CUSTOMER)
                    .build();
        }
        throw new CouponSecurityException(SecurityMessage.LOGIN_FAIL);
    }
}
