package com.jb.couponSystemPhaseTwo.clr.on.controllerTesting;

import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.dto.LoginReqDto;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSecurityException;
import com.jb.couponSystemPhaseTwo.exceptions.SecurityMessage;
import com.jb.couponSystemPhaseTwo.services.ClientType;
import com.jb.couponSystemPhaseTwo.utils.MessageColor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
@Order(11)
public class AdminControllerTesting extends ControllerTesting implements CommandLineRunner {
    private String banner = MessageColor.ANSI_BG_GREEN.getTextColor() + MessageColor.ANSI_BLACK.getTextColor() +
            "\t\t\t\t  __                   __  .__                                      __                .__  .__                       \n" +
            "\t\t\t\t_/  |_  ____   _______/  |_|__| ____    ____     ____  ____   _____/  |________  ____ |  | |  |   ___________  ______\n" +
            "\t\t\t\t\\   __\\/ __ \\ /  ___/\\   __\\  |/    \\  / ___\\  _/ ___\\/  _ \\ /    \\   __\\_  __ \\/  _ \\|  | |  | _/ __ \\_  __ \\/  ___/\n" +
            "\t\t\t\t |  | \\  ___/ \\___ \\  |  | |  |   |  \\/ /_/  > \\  \\__(  <_> )   |  \\  |  |  | \\(  <_> )  |_|  |_\\  ___/|  | \\/\\___ \\ \n" +
            "\t\t\t\t |__|  \\___  >____  > |__| |__|___|  /\\___  /   \\___  >____/|___|  /__|  |__|   \\____/|____/____/\\___  >__|  /____  >\n" +
            "\t\t\t\t           \\/     \\/               \\//_____/        \\/           \\/                                  \\/           \\/ \n\n" +
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
    private final LoginReqDto loginAdminDto = LoginReqDto.builder()
            .email("admin@admin.com")
            .password("admin")
            .clientType(ClientType.ADMINISTRATOR)
            .build();
    private final LoginReqDto loginCompanyDto = LoginReqDto.builder()
            .email("email@myaddress.com")
            .password("new pass")
            .clientType(ClientType.COMPANY)
            .build();
    private final LoginReqDto loginCustomerDto = LoginReqDto.builder()
            .email("email@address.com")
            .password("updated pass")
            .clientType(ClientType.CUSTOMER)
            .build();
    private final LoginReqDto loginFailDto = LoginReqDto.builder()
            .email("admin@wrong.com")
            .password("adfmin")
            .clientType(ClientType.ADMINISTRATOR)
            .build();

    @Override
    public void run(String... args) throws Exception {
        System.out.println(banner);
        controlDescription("\t\ttesting adminController\n");
        getAllCompanies(UUID.randomUUID());
        addCompany(company);
        addCompany(company);
        updateCompany(1000, companyToUpdate);
        updateCompany(10, companyToUpdate);
        updateCompany(11, companyToUpdate);
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
        login(loginAdminDto);
        login(loginCompanyDto);
        login(loginCustomerDto);
        login(loginFailDto);
        controlDescription("\t\ttesting adminController ended\n");
    }

    // companies functions
    private void addCompany(Company company) {
        HttpEntity<Company> add = new HttpEntity<>(company);
        try {
            ResponseEntity<Company> res = restTemplate.exchange(url + "/companies", HttpMethod.POST, add, Company.class);
            successDescription("|--->\tadmin addCompany success. response status is: " + (res.getStatusCodeValue()));
            getAllCompanies(UUID.randomUUID());
        } catch (Exception e) {
            failDescription("|--->\tadmin addCompany fail");
            System.out.println(e.getMessage());
        }
    }

    private void updateCompany(int companyId, Company company) {
        company.setId(companyId);
        try {
            HttpEntity<Company> update = new HttpEntity<>(company);
            ResponseEntity<Company> res = restTemplate.exchange(url + "/companies/" + companyId, HttpMethod.PUT, update, Company.class);
            successDescription("|--->\tadmin updateCompany success. response status is: " + (res.getStatusCodeValue()));
            getAllCompanies(UUID.randomUUID());
        } catch (Exception e) {
            failDescription("|--->\tadmin updateCompany fail");
            System.out.println(e.getMessage());
        }
    }

    private void deleteCompany(int companyId) {
        try {
            ResponseEntity<Company> res = restTemplate.exchange(url + "/companies/" + companyId, HttpMethod.DELETE, null, Company.class);
            successDescription("|--->\tadmin deleteCompany success. response status is: " + (res.getStatusCodeValue()));
            getAllCompanies(UUID.randomUUID());
        } catch (Exception e) {
            failDescription("|--->\tadmin deleteCompany fail");
            System.out.println(e.getMessage());
        }
    }

    private void getAllCompanies(UUID token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token.toString());
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        try {
            ResponseEntity<List<Company>> res =
                    restTemplate.exchange(url + "/companies", HttpMethod.GET, entity, new ParameterizedTypeReference<List<Company>>() {
                    });
            successDescription("|--->\tadmin getAllCompanies success. response status is: " + (res.getStatusCodeValue()));
            controlDescription("|--->\tadmin getAllCompanies. response status is: " + (res.getStatusCodeValue()));
            Objects.requireNonNull(res.getBody()).forEach(System.out::println);
        } catch (Exception e) {
            failDescription("|--->\tadmin getAllCompanies fail");
            System.out.println(e.getMessage());
        }
    }

    private void getOneCompany(int companyId) {
        try {
            ResponseEntity<Company> res = restTemplate.exchange(url + "/companies/" + companyId, HttpMethod.GET, null, new ParameterizedTypeReference<Company>() {
            });
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
        } catch (Exception e) {
            failDescription("|--->\tadmin addCustomer fail");
            System.out.println(e.getMessage());
        }
    }

    private void updateCustomer(int customerId, Customer customer) {
        customer.setId(customerId);
        try {
            HttpEntity<Customer> update = new HttpEntity<>(customer);
            ResponseEntity<Customer> res = restTemplate.exchange(url + "/customers/" + customerId, HttpMethod.PUT, update, Customer.class);
            successDescription("|--->\tadmin updateCustomer success. response status is: " + (res.getStatusCodeValue()));
            getAllCustomers();
        } catch (Exception e) {
            failDescription("|--->\tadmin updateCustomer fail");
            System.out.println(e.getMessage());
        }
    }

    private void deleteCustomer(int customerId) {
        try {
            ResponseEntity<Customer> res = restTemplate.exchange(url + "/customers/" + customerId, HttpMethod.DELETE, null, Customer.class);
            successDescription("|--->\tadmin deleteCustomer success. response status is: " + (res.getStatusCodeValue()));
            getAllCustomers();
        } catch (Exception e) {
            failDescription("|--->\tadmin deleteCustomer fail");
            System.out.println(e.getMessage());
        }
    }

    private void getAllCustomers() {
        ResponseEntity<List<Customer>> res = restTemplate.exchange(url + "/customers", HttpMethod.GET, null, new ParameterizedTypeReference<List<Customer>>() {
        });
        controlDescription("|--->\tadmin getAllCustomers. response status is: " + (res.getStatusCodeValue()));
        Objects.requireNonNull(res.getBody()).forEach(System.out::println);
    }

    private void getOneCustomer(int customerId) {
        try {
            ResponseEntity<Customer> res = restTemplate.exchange(url + "/customers/" + customerId, HttpMethod.GET, null, new ParameterizedTypeReference<Customer>() {
            });
            successDescription("|--->\tadmin getOneCustomer success. response status is: " + (res.getStatusCodeValue()));
            System.out.println(res.getBody());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void login(LoginReqDto loginReqDto) {
        HttpEntity<LoginReqDto> add = new HttpEntity<>(loginReqDto);
        try {
            ResponseEntity<UUID> res = restTemplate.exchange(url + "/login", HttpMethod.POST, add, UUID.class);
            successDescription("|--->\tlogin success. response status is: " + (res.getStatusCodeValue()));
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                try {
                    throw new CouponSecurityException(SecurityMessage.LOGIN_FAIL);
                } catch (CouponSecurityException ex) {
                    failDescription("|--->\tlogin fail: " + ex.getStatus().value() + ex.getMessage()); // Print exception message here
                }
            }
        } catch (Exception e) {
            failDescription("|--->\tlogin fail: " + e.getMessage()); // Print exception message here
        }
    }


}
