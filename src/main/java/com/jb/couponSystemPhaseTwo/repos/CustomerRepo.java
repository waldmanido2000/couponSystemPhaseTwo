package com.jb.couponSystemPhaseTwo.repos;

import com.jb.couponSystemPhaseTwo.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository <Customer,Integer> {
    boolean existsByEmailAndPassword(String email, String password);
//    @Query(value = "INSERT INTO `coupon-system-phase-two`.`customers_coupons` (`customer_id`, `coupons_id`) VALUES (?,?);", nativeQuery = true)
//    void addPurchase(int customer_id, int coupon_id);
    @Query(value = "DELETE FROM `coupon-system-phase-two`.`customers_coupons` WHERE (`customer_id` = ?) and (`coupons_id` = ?);\n", nativeQuery = true)
    void deletePurchase(int customer_id, int coupon_id);
}
