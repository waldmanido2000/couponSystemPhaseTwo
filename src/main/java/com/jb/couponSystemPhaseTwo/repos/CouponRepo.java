package com.jb.couponSystemPhaseTwo.repos;

import com.jb.couponSystemPhaseTwo.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface CouponRepo extends JpaRepository <Coupon, Integer> {
    @Query
    boolean existsByCompany_idAndTitle(int Company_id, String title);
    @Query
    List<Coupon> findByCompany_id(int companyId);
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
    @Query(value = "DELETE from `coupon-system-phase-two`.`customers_coupons` WHERE `customer_id` = ?;", nativeQuery = true)
    void deletePurchasesByCustomer(int customer_id);

    @Query(value = "SELECT * FROM `coupon-system-phase-two`.coupons where (`company_id` = ?) and (price < ?);", nativeQuery = true)
    List<Coupon> findByCompany_idAndByMaxPrice(int companyId, double maxPrice);
    @Query(value = "SELECT * FROM `coupon-system-phase-two`.coupons where (`company_id` = ?) and (category = ?);", nativeQuery = true)
    List<Coupon> findByCompany_idAndByCategory(int companyId, String category);
}
