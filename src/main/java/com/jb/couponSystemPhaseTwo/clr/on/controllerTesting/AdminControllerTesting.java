package com.jb.couponSystemPhaseTwo.clr.on.controllerTesting;

import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import com.jb.couponSystemPhaseTwo.utils.MessageColor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Order(11)
public class AdminControllerTesting extends ControllerTesting implements CommandLineRunner {
private String banner = MessageColor.ANSI_BG_GREEN.getTextColor() + MessageColor.ANSI_BLACK.getTextColor() +
        "\t\t\t\t  __                   __  .__                                      __                .__  .__                       \n" +
        "\t\t\t\t_/  |_  ____   _______/  |_|__| ____    ____     ____  ____   _____/  |________  ____ |  | |  |   ___________  ______\n" +
        "\t\t\t\t\\   __\\/ __ \\ /  ___/\\   __\\  |/    \\  / ___\\  _/ ___\\/  _ \\ /    \\   __\\_  __ \\/  _ \\|  | |  | _/ __ \\_  __ \\/  ___/\n" +
        "\t\t\t\t |  | \\  ___/ \\___ \\  |  | |  |   |  \\/ /_/  > \\  \\__(  <_> )   |  \\  |  |  | \\(  <_> )  |_|  |_\\  ___/|  | \\/\\___ \\ \n" +
        "\t\t\t\t |__|  \\___  >____  > |__| |__|___|  /\\___  /   \\___  >____/|___|  /__|  |__|   \\____/|____/____/\\___  >__|  /____  >\n" +
        "\t\t\t\t           \\/     \\/               \\//_____/        \\/           \\/                                  \\/           \\/ \n\n"+
        MessageColor.ANSI_BG_BLACK.getTextColor();
    @Value("${url}")
    private String url;
    private final Company company = Company.builder()
            .email("email@myaddress.com")
            .name("new controller company")
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
        System.out.println(banner);
        controlDescription("\t\ttesting adminController\n");
        addCompany(company);
//        updateCompany(10, company);
        deleteCompany(7);
        getOneCompany(8);
//        addCustomer(customer);
//        updateCustomer(10, customer);
//        deleteCustomer(9);
//        getOneCustomer(11);
        controlDescription("\t\ttesting adminController ended\n");
    }
    // companies functions
    private void addCompany(Company company) throws SQLException, CouponSystemException {
        controlDescription("|--->\tcompanies");
        getAllCompanies().forEach(System.out::println);
        successDescription("|--->\tadmin addCompany success");
        ResponseEntity<String> res = restTemplate.postForEntity(url + "/companies/add", company, String.class);
        System.out.println((res.getStatusCodeValue() == 201) ? "SABABA" : "Something went wrong");
        controlDescription("|--->\tcompanies");
        getAllCompanies().forEach(System.out::println);
    }

    private void updateCompany(int companyId, Company company) throws SQLException, CouponSystemException{
        successDescription("|--->\tadmin addCompany success");
        System.out.println(restTemplate.postForEntity(url + "/companies/add", company, String.class).getStatusCode());
        controlDescription("|--->\tcompanies");
        getAllCompanies().forEach(System.out::println);
        // TODO: 12/5/2022
    }

    private void deleteCompany(int companyId) throws SQLException, CouponSystemException {
        successDescription("|--->\tadmin deleteCompany success");
        restTemplate.delete(url + "/companies/company/"+companyId);
        controlDescription("|--->\tcompanies");
        getAllCompanies().forEach(System.out::println);
    }

    private List<Company> getAllCompanies() throws SQLException {
        return new ArrayList<>(List.of(Objects.requireNonNull(restTemplate.getForObject(url + "/companies", Company[].class))));
    }

    private Company getOneCompany(int companyId) throws SQLException, CouponSystemException {
        successDescription("|--->\tadmin get one Company success");
        Company company1 = (restTemplate.getForObject(url + "/companies/company/"+companyId, Company.class));
        System.out.println(company1);
        return company1;
    }

    // customers functions
    private void addCustomer(Customer customer) throws SQLException, CouponSystemException{
        // TODO: 12/5/2022
    }

    private void updateCustomer(int customerId, Customer customer) throws SQLException, CouponSystemException{
        // TODO: 12/5/2022
    }

    private void deleteCustomer(int customerId) throws SQLException, CouponSystemException{
        // TODO: 12/5/2022
    }

    private List<Customer> getAllCustomers() throws SQLException{
        // TODO: 12/5/2022
        return null;
    }
    private Customer getOneCustomer(int customerId) throws SQLException, CouponSystemException{
        // TODO: 12/5/2022
        return null;
    }
}
