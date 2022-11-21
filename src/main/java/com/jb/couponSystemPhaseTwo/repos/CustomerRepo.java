package com.jb.couponSystemPhaseTwo.repos;

import com.jb.couponSystemPhaseTwo.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    boolean existsByEmailAndPassword(String email, String password);

    @Modifying
    @Query(value = "INSERT INTO `coupon-system-phase-two`.`customers_coupons` (`customer_id`, `coupons_id`) VALUES (?,?);", nativeQuery = true)
    void addPurchase(int customer_id, int coupon_id);

    @Modifying
    @Query(value = "DELETE FROM `coupon-system-phase-two`.`customers_coupons` WHERE (`customer_id` = ?) and (`coupons_id` = ?);", nativeQuery = true)
    void deletePurchase(int customer_id, int coupon_id);
}
