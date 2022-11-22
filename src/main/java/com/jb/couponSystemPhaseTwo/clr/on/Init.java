package com.jb.couponSystemPhaseTwo.clr.on;

import com.jb.couponSystemPhaseTwo.beans.Category;
import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import com.jb.couponSystemPhaseTwo.exceptions.ErrorMessage;
import com.jb.couponSystemPhaseTwo.repos.CompanyRepo;
import com.jb.couponSystemPhaseTwo.repos.CouponRepo;
import com.jb.couponSystemPhaseTwo.repos.CustomerRepo;
import com.jb.couponSystemPhaseTwo.utils.Debug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

@Component
@Order(1)
public class Init implements CommandLineRunner {
    private static final String FLAG_NAME = "DB_MANAGER_DEBUG_MODE";
    @Autowired
    private CouponRepo couponRepo;
    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private CustomerRepo customerRepo;
    private Random random = new Random();

    @Override
    public void run(String... args) throws Exception {


            showDescription("\n***************** demoEntities start ******************\n");
            demoEntities();
            showDescription("\n***************** demoEntities end ******************\n");
    }


    private void demoEntities() throws SQLException {
        showDescription("altering customers_coupons table pks");
        showDescription("adding entities to DB:");
        addCompanies();
        companyRepo.findAll().forEach(System.out::println);
        addCoupons();

        addCustomers();
        customerRepo.findAll().forEach(System.out::println);
        addCouponPurchase();
        customerRepo.addPurchase(10,80);
    }

    private void addCompanies() {
        showDescription("\n|--->\tadding companies to DB\n");
        List<Company> companies = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Company company = Company.builder()
                    .email("www@company" + i)
                    .password("pass" + i)
                    .name("name " + i)
                    .build();
            companies.add(company);
        }
        companyRepo.saveAll(companies);
    }

    private void addCoupons() {
        showDescription("\n|--->\tadding coupons to DB\n");
        List<Coupon> coupons = new ArrayList<>();
        for (Company company:
             companyRepo.findAll()) {
            for (int i = 1; i < 11; i++) {
                LocalDate randomLocalDate = LocalDate.now();
                Date startDate = Date.valueOf(randomLocalDate);
                Date endDate = Date.valueOf(randomLocalDate.plusDays(random.nextInt(28)));
                Coupon couponTemp = Coupon.builder()
                        .startDate(startDate)
                        .endDate(endDate)
                        .amount(random.nextInt(200) + 100)
                        .category(Category.values()[(i) % 6])
                        .company(company)
                        .image( "url" + i)
                        .price(random.nextInt(300) + 1)
                        .title("title " + i)
                        .description("description " + i)
                        .build();
                coupons.add(couponTemp);
            }
            coupons.forEach(System.out::println);
            couponRepo.saveAll(coupons);
        }
    }
    private void addCustomers() {
        showDescription("\n|--->\tadding customers to DB\n");
        List<Customer> customers = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Customer customer = Customer.builder()
                    .firstName("first " + i)
                    .lastName("last " + i)
                    .password("pass" + i)
                    .email("www@customer" + i)
                    .build();
            customers.add(customer);
        }
        customerRepo.saveAll(customers);
    }
    private void addCouponPurchase(){
        showDescription("|--->\tadding purchases to DB");
        for (int i = 1; i <= customerRepo.count(); i++) {
            for (int j = 0; j < 5; j++) {
                customerRepo.addPurchase(i,i*10-j);
            }
        }
    }

    private static void showDescription(String description) {
        Debug.showDescription(FLAG_NAME, description);
    }
}
