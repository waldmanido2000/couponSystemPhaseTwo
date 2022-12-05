package com.jb.couponSystemPhaseTwo.clr.on.serviceTesting;

import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@Order(3)
public class AdminServiceTesting extends ServicesTesting implements CommandLineRunner {
    private final Company company = Company.builder()
            .email("email@address.com")
            .name("new company")
            .password("new pass")
            .build();
    private final Customer customer = Customer.builder()
            .firstName("new")
            .lastName("customer")
            .email("email@address.com")
            .password("new pass")
            .build();

    @Override
    public void run(String... args) throws Exception {
        controlDescription("\t\ttesting adminService\n");
        addCompany(company);
        updateCompany(10, company);
        deleteCompany(9);
        getOneCompany(11);
        addCustomer(customer);
        updateCustomer(10, customer);
        deleteCustomer(9);
        getOneCustomer(11);
        controlDescription("\t\ttesting adminService ended\n");
    }

    // companies functions
    private void addCompany(Company company) throws SQLException {
        controlDescription("|--->\tcompanies");
        adminService.getAllCompanies().forEach(System.out::println);
        failDescription("|--->\tadmin addCompany (name already exists)");
        company.setName("name 1");
        try {
            adminService.addCompany(company);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        failDescription("|--->\tadmin addCompany (email already exists)");
        company.setName("new name");
        company.setEmail("www@company1");
        try {
            adminService.addCompany(company);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        successDescription("|--->\tadmin addCompany success");
        company.setName("new name");
        company.setEmail("new email address");
        try {
            adminService.addCompany(company);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        controlDescription("|--->\tcompanies");
        adminService.getAllCompanies().forEach(System.out::println);
    }

    private void updateCompany(int companyId, Company company) throws SQLException {
        failDescription("|--->\tadmin updateCompany (company id not exists)");
        try {
            adminService.updateCompany(500, company);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        failDescription("|--->\tadmin updateCompany (change company name)");
        company.setName("new name");
        company.setEmail("www@company10");
        company.setPassword("updated password");
        try {
            adminService.updateCompany(companyId, company);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        successDescription("|--->\tadmin updateCompany success");
        company.setName("name 10");
        try {
            adminService.updateCompany(companyId, company);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        controlDescription("|--->\tcompanies");
        adminService.getAllCompanies().forEach(System.out::println);
    }

    private void deleteCompany(int companyId) throws SQLException {
        successDescription("|--->\tadmin deleteCompany success");
        try {
            adminService.deleteCompany(companyId);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        controlDescription("|--->\tcompanies");
        adminService.getAllCompanies().forEach(System.out::println);
    }

    private void getOneCompany(int companyId) throws SQLException {
        successDescription("|--->\tadmin get one Company success");
        try {
            Company company = adminService.getOneCompany(companyId);
            System.out.printf("id = %s, name = %s, email = %s, password = %s\n%n",
                    company.getId(), company.getName(), company.getEmail(), company.getPassword());

        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
    }

    // customers functions
    private void addCustomer(Customer customer) throws SQLException {
        controlDescription("|--->\tcustomers");
        adminService.getAllCustomers().forEach(System.out::println);
        failDescription("|--->\tadmin add customer (email exists)");
        customer.setEmail("www@customer3");
        try {
            adminService.addCustomer(customer);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        successDescription("|--->\tadmin add customer success");
        customer.setEmail("new email");
        try {
            adminService.addCustomer(customer);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        controlDescription("|--->\tcustomers");
        adminService.getAllCustomers().forEach(System.out::println);
    }

    private void updateCustomer(int customerId, Customer customer) throws SQLException {
        failDescription("|--->\tadmin update customer (customer not exists)");
        try {
            adminService.updateCustomer(900,customer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        successDescription("|--->\tadmin update customer success");
        customer.setPassword("updated password");
        try {
            adminService.updateCustomer(customerId,customer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        controlDescription("|--->\tcustomers");
        adminService.getAllCustomers().forEach(System.out::println);
    }

    private void deleteCustomer(int customerId) throws SQLException {
        failDescription("|--->\tadmin deleteCustomer (customer not exists)");
        try {
            adminService.deleteCustomer(800);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        controlDescription("|--->\tcustomers");
        successDescription("|--->\tadmin deleteCustomer success");
        try {
            adminService.deleteCustomer(customerId);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        controlDescription("|--->\tcustomers");
        adminService.getAllCustomers().forEach(System.out::println);
    }

    private void getOneCustomer(int customerId) throws SQLException {
        successDescription("|--->\tadmin get one customer success");
        try {
            Customer customer = adminService.getOneCustomer(customerId);
            System.out.printf("id = %s, first name = %s, last name = %s, email = %s, password = %s\n%n",
                    customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPassword());
        }
        catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
    }
}
