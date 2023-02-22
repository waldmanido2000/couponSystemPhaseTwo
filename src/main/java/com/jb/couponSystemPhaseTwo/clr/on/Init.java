package com.jb.couponSystemPhaseTwo.clr.on;

import com.jb.couponSystemPhaseTwo.beans.Category;
import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.repos.CompanyRepo;
import com.jb.couponSystemPhaseTwo.repos.CouponRepo;
import com.jb.couponSystemPhaseTwo.repos.CustomerRepo;
import com.jb.couponSystemPhaseTwo.utils.Debug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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


    private void demoEntities() {
        showDescription("altering customers_coupons table pks");
        showDescription("adding entities to DB:");
        addCompanies();
        addCoupons();
        addCustomers();
        addCouponPurchase();
        customerRepo.addPurchase(10, 80);
    }

    private void addCompanies() {
        showDescription("\n|--->\tadding companies to DB\n");
        List<Company> companies = new ArrayList<>();
        List<String> emails = Arrays.asList("acme@gmail.com", "contoso@gmail.com", "adatum@gmail.com", "northwind@gmail.com",
                "proseware@gmail.com", "noacme@gmail.com", "nocontoso@gmail.com", "noadatum@gmail.com", "nonorthwind@gmail.com", "noproseware@gmail.com");
        List<String> passwords = Arrays.asList("acme123", "contoso123", "adatum123", "northwind123",
                "proseware123", "noacme123", "nocontoso123", "noadatum123", "nonorthwind123", "noproseware123");
        List<String> names = Arrays.asList("Acme Inc.", "Contoso Ltd.", "Adatum Corp.", "Northwind Traders",
                "Proseware Inc.", "noAcme Inc.", "noContoso Ltd.", "noAdatum Corp.", "noNorthwind Traders", "noProseware Inc.");
        for (int i = 0; i < 10; i++) {
            companies.add(Company.builder()
                    .email(emails.get(i))
                    .password(passwords.get(i))
                    .name(names.get(i))
                    .build());
        }
        companyRepo.saveAll(companies);
    }

    private void addCoupons() {
        showDescription("\n|--->\tadding coupons to DB\n");
        List<Coupon> coupons = new ArrayList<>();
        for (Company company :
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
                        .image(company.getName().toLowerCase().replaceAll("\\s+", "") + i + ".jpg")
                        .price(random.nextInt(300) + 1)
                        .title(company.getName().toLowerCase().replaceAll("\\s+", "") + " coupon " + i)
                        .description(company.getName().toLowerCase().replaceAll("\\s+", "") + " discount for " + i + "%")
                        .build();
                coupons.add(couponTemp);
            }
//            for (int i = 1; i < 3; i++) {
//                LocalDate randomLocalDate = LocalDate.now();
//                Date startDate = Date.valueOf(randomLocalDate);
//                Date endDate = Date.valueOf(randomLocalDate.plusDays(random.nextInt(28)));
//                Coupon couponTemp = Coupon.builder()
//                        .startDate(startDate)
//                        .endDate(endDate)
//                        .amount(0)
//                        .category(Category.values()[(i) % 6])
//                        .company(company)
//                        .image(company.getName().toLowerCase().replaceAll("\\s+", "") + i + ".jpg")
//                        .price(random.nextInt(300) + 1)
//                        .title(company.getName().toLowerCase().replaceAll("\\s+", "") + " coupon " + i)
//                        .description(company.getName().toLowerCase().replaceAll("\\s+", "") + " discount for " + i + "%")
//                        .build();
//                coupons.add(couponTemp);
//            }
//            for (int i = 1; i < 3; i++) {
//                LocalDate randomLocalDate = LocalDate.now();
//                Date startDate = Date.valueOf(randomLocalDate.minusDays(10));
//                Date endDate = Date.valueOf(randomLocalDate.minusDays(1));
//                String[] randomTitles = {"Expired Coupon", "Old Offer", "Past Deal"};
//                String[] randomDescriptions = {"This coupon has already expired", "This offer is no longer valid", "This deal has passed its expiration date"};
//                Coupon coupon = Coupon.builder()
//                        .startDate(startDate)
//                        .endDate(endDate)
//                        .amount(random.nextInt(200) + 100)
//                        .category(Category.values()[(i) % 6])
//                        .company(company)
//                        .image("https://example.com/images/expired_coupon_" + i)
//                        .price(random.nextInt(300) + 1)
//                        .title(randomTitles[i - 1])
//                        .description(randomDescriptions[i - 1])
//                        .build();
//                coupons.add(coupon);
//            }
            couponRepo.saveAll(coupons);
        }
    }

    private void addCustomers() {
        showDescription("\n|--->\tadding customers to DB\n");
        List<String> firstNames = Arrays.asList("John", "Jane", "Bob", "Alice", "Charlie", "Emily", "Ethan", "Ava", "Olivia", "William");
        List<String> lastNames = Arrays.asList("Doe", "Smith", "Johnson", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson");
        List<String> passwords = Arrays.asList("john123", "jane123", "bob123", "alice123", "charlie123", "emily123", "ethan123", "ava123", "olivia123", "william123");
        List<String> emails = Arrays.asList("johndoe@gmail.com", "janesmith@gmail.com", "bobjohnson@gmail.com", "alicebrown@gmail.com", "charliedavis@gmail.com",
                "emilymiller@gmail.com", "ethanwilson@gmail.com", "avamoore@gmail.com", "oliviataylor@gmail.com", "williamanderson@gmail.com");

        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            customers.add(Customer.builder()
                    .firstName(firstNames.get(i))
                    .lastName(lastNames.get(i))
                    .password(passwords.get(i))
                    .email(emails.get(i))
                    .build());
        }
        customerRepo.saveAll(customers);
    }

    private void addCouponPurchase() {
        showDescription("|--->\tadding purchases to DB");
        for (int i = 1; i <= customerRepo.count(); i++) {
            for (int j = 0; j < 5; j++) {
                customerRepo.addPurchase(i, i * 10 - j);
            }
        }
    }

    private static void showDescription(String description) {
        Debug.showDescription(FLAG_NAME, description);
    }
}
