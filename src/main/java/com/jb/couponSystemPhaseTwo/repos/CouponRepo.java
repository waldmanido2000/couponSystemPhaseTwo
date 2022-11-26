package com.jb.couponSystemPhaseTwo.repos;

import com.jb.couponSystemPhaseTwo.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Repository
public interface CouponRepo extends JpaRepository <Coupon, Integer> {
    @Query
    boolean existsByCompany_idAndTitle(int Company_id, String title);
    @Query
    List<Coupon> findByCompany_id(int companyId);
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `coupon-system-phase-two`.`customers_coupons` (`customer_id`, `coupons_id`) VALUES (?, ?);\n", nativeQuery = true)
    void addPurchase(int customer_id, int coupon_id);
    @Query
    @Transactional
    @Modifying
    void deleteCouponByCompany_id(int company_id);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `coupon-system-phase-two`.`customers_coupons` WHERE (`coupons_id` = ?);", nativeQuery = true)
    void deletePurchases(int coupon_id);
    @Transactional
    @Modifying
    @Query(value = "DELETE from `coupon-system-phase-two`.customers_coupons WHERE coupons_id IN (SELECT id FROM `coupon-system-phase-two`.coupons where (company_id = ?));", nativeQuery = true)
    void deletePurchasesByCompany(int company_id);
    @Transactional
    @Modifying
    @Query(value = "DELETE from `coupon-system-phase-two`.customers_coupons WHERE coupons_id IN (SELECT id FROM `coupon-system-phase-two`.coupons where (end_date < ?));", nativeQuery = true)
    void deleteObsoletePurchases(Date today);
    @Transactional
    @Modifying
    @Query(value = "delete from `coupon-system-phase-two`.coupons where (end_date < ?);", nativeQuery = true)
    void deleteObsoleteCoupons(Date today);
    @Transactional
    @Modifying
    @Query(value = "DELETE from `coupon-system-phase-two`.`customers_coupons` WHERE `customer_id` = ?;", nativeQuery = true)
    void deletePurchasesByCustomer(int customer_id);

    @Query(value = "SELECT * FROM `coupon-system-phase-two`.coupons where (`company_id` = ?) and (price < ?);", nativeQuery = true)
    List<Coupon> findByCompany_idAndByMaxPrice(int companyId, double maxPrice);
    @Query(value = "SELECT * FROM `coupon-system-phase-two`.coupons where (`company_id` = ?) and (category = ?);", nativeQuery = true)
    List<Coupon> findByCompany_idAndByCategory(int companyId, String category);
    @Query(value = "SELECT COUNT(*)  as res FROM `coupon-system-phase-two`.customers_coupons where customer_id = ? and coupons_id = ?;" ,nativeQuery = true)
    int isPurchased(int customer_id, int coupon_id);

    @Query(value = "select * from `coupon-system-phase-two`.coupons where id in (SELECT `coupon-system-phase-two`.coupons.id as res\n" +
            "FROM `coupon-system-phase-two`.coupons \n" +
            "JOIN `coupon-system-phase-two`.customers_coupons \n" +
            "ON `coupon-system-phase-two`.`customers_coupons`.coupons_id = `coupon-system-phase-two`.coupons.id \n" +
            "where `coupon-system-phase-two`.`customers_coupons`.customer_id=?);", nativeQuery = true)
    List<Coupon> findPurchasesByCustomer(int customer_id);
    @Query(value = "select * from `coupon-system-phase-two`.coupons where id in (SELECT `coupon-system-phase-two`.coupons.id as res\n" +
            "FROM `coupon-system-phase-two`.coupons \n" +
            "JOIN `coupon-system-phase-two`.customers_coupons \n" +
            "ON `coupon-system-phase-two`.`customers_coupons`.coupons_id = `coupon-system-phase-two`.coupons.id \n" +
            "where (`coupon-system-phase-two`.`customers_coupons`.customer_id=?) and category = ?)", nativeQuery = true)
    List<Coupon> findPurchasesByCustomerAndCategory(int customer_id, String category);
    @Query(value = "select * from `coupon-system-phase-two`.coupons where id in (SELECT `coupon-system-phase-two`.coupons.id as res\n" +
            "FROM `coupon-system-phase-two`.coupons \n" +
            "JOIN `coupon-system-phase-two`.customers_coupons \n" +
            "ON `coupon-system-phase-two`.`customers_coupons`.coupons_id = `coupon-system-phase-two`.coupons.id \n" +
            "where (`coupon-system-phase-two`.`customers_coupons`.customer_id=?) and price < ?)", nativeQuery = true)
    List<Coupon> findPurchasesByCustomerAndMaxPrice(int customer_id, double maxPrice);
}
