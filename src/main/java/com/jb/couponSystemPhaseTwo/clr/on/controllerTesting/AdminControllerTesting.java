package com.jb.couponSystemPhaseTwo.clr.on.controllerTesting;

import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.utils.MessageColor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

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
    private final Company companyToUpdate = Company.builder()
            .email("email@myaddress.com")
            .name("new name")
            .password("new pass")
            .build();
    private final Customer customer = Customer.builder()
            .firstName("new")
            .lastName("customer")
            .email("email@address.com")
            .password("new pass")
            .build();
    private final Customer customerToUpdate = Customer.builder()
            .firstName("new")
            .lastName("customer")
            .email("email@address.com")
            .password("updated pass")
            .build();
    @Override
    public void run(String... args) throws Exception {
        System.out.println(banner);
        controlDescription("\t\ttesting adminController\n");
        getAllCompanies();
        addCompany(company);
        addCompany(company);
        updateCompany(1000,companyToUpdate);
        updateCompany(10,companyToUpdate);
        updateCompany(11,companyToUpdate);
        deleteCompany(7);
        deleteCompany(7);
        getOneCompany(8);
        getAllCustomers();
        addCustomer(customer);
        addCustomer(customer);
        updateCustomer(1000, customerToUpdate);
        updateCustomer(10, customerToUpdate);
        deleteCustomer(7);
        deleteCustomer(7);
        getOneCustomer(8);
        controlDescription("\t\ttesting adminController ended\n");
    }
    // companies functions
    private void addCompany(Company company) {
        HttpEntity<Company> add = new HttpEntity<>(company);
        try {
            ResponseEntity<Company> res = restTemplate.exchange(url + "/companies", HttpMethod.POST, add, Company.class);
            successDescription("|--->\tadmin addCompany success. response status is: " + (res.getStatusCodeValue()));
            getAllCompanies();
        }
        catch (Exception e) {
            failDescription("|--->\tadmin addCompany fail");
            System.out.println(e.getMessage());
        }
    }

    private void updateCompany(int companyId, Company company) {
        company.setId(companyId);
        try {
            HttpEntity<Company> update = new HttpEntity<>(company);
            ResponseEntity<Company> res = restTemplate.exchange(url+"/companies/company/"+companyId, HttpMethod.PUT,update,Company.class);
            successDescription("|--->\tadmin updateCompany success. response status is: " + (res.getStatusCodeValue()));
            getAllCompanies();
        }
        catch (Exception e) {
            failDescription("|--->\tadmin updateCompany fail");
            System.out.println(e.getMessage());
        }
    }

    private void deleteCompany(int companyId) {
        try {
            ResponseEntity<Company> res = restTemplate.exchange(url + "/companies/company/"+companyId, HttpMethod.DELETE, null, Company.class);
            successDescription("|--->\tadmin deleteCompany success. response status is: " + (res.getStatusCodeValue()));
            getAllCompanies();
        } catch (Exception e) {
            failDescription("|--->\tadmin deleteCompany fail");
            System.out.println(e.getMessage());
        }
    }

    private void getAllCompanies() {
        ResponseEntity<List<Company>> res = restTemplate.exchange(url + "/companies", HttpMethod.GET, null, new ParameterizedTypeReference<List<Company>>() {});
        controlDescription("|--->\tadmin getAllCompanies. response status is: " + (res.getStatusCodeValue()));
        Objects.requireNonNull(res.getBody()).forEach(System.out::println);
    }

    private void getOneCompany(int companyId) {
        try {
            ResponseEntity<Company> res = restTemplate.exchange(url + "/companies/company/"+companyId, HttpMethod.GET, null, new ParameterizedTypeReference<Company>() {});
            controlDescription("|--->\tadmin getOneCompany success. response status is: " + (res.getStatusCodeValue()));
            System.out.println(res.getBody());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // customers functions
    private void addCustomer(Customer customer) {
        HttpEntity<Customer> add = new HttpEntity<>(customer);
        try {
            ResponseEntity<Customer> res = restTemplate.exchange(url + "/customers", HttpMethod.POST, add, Customer.class);
            successDescription("|--->\tadmin addCustomer success. response status is: " + (res.getStatusCodeValue()));
            getAllCustomers();
        }
        catch (Exception e) {
            failDescription("|--->\tadmin addCustomer fail");
            System.out.println(e.getMessage());
        }
    }

    private void updateCustomer(int customerId, Customer customer) {
        customer.setId(customerId);
        try {
            HttpEntity<Customer> update = new HttpEntity<>(customer);
            ResponseEntity<Customer> res = restTemplate.exchange(url+"/customers/customer/"+customerId, HttpMethod.PUT,update,Customer.class);
            successDescription("|--->\tadmin updateCustomer success. response status is: " + (res.getStatusCodeValue()));
            getAllCustomers();
        }
        catch (Exception e) {
            failDescription("|--->\tadmin updateCustomer fail");
            System.out.println(e.getMessage());
        }
    }

    private void deleteCustomer(int customerId) {
        try {
            ResponseEntity<Customer> res = restTemplate.exchange(url + "/customers/customer/"+customerId, HttpMethod.DELETE, null, Customer.class);
            successDescription("|--->\tadmin deleteCustomer success. response status is: " + (res.getStatusCodeValue()));
            getAllCustomers();
        } catch (Exception e) {
            failDescription("|--->\tadmin deleteCustomer fail");
            System.out.println(e.getMessage());
        }
    }

    private void getAllCustomers() {
        ResponseEntity<List<Customer>> res = restTemplate.exchange(url + "/customers", HttpMethod.GET, null, new ParameterizedTypeReference<List<Customer>>() {});
        controlDescription("|--->\tadmin getAllCustomers. response status is: " + (res.getStatusCodeValue()));
        Objects.requireNonNull(res.getBody()).forEach(System.out::println);
    }

    private void getOneCustomer(int customerId) {
        try {
            ResponseEntity<Customer> res = restTemplate.exchange(url + "/customers/customer/"+customerId, HttpMethod.GET, null, new ParameterizedTypeReference<Customer>() {});
            controlDescription("|--->\tadmin getOneCustomer success. response status is: " + (res.getStatusCodeValue()));
            System.out.println(res.getBody());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
