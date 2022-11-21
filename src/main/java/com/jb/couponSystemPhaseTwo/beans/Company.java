package com.jb.couponSystemPhaseTwo.beans;

import com.jb.couponSystemPhaseTwo.CouponSystemPhaseTwoApplication;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generated value per entity
    private int id;
    @Column(length = 40, nullable = false)
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "company", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @Singular
    private List<Coupon> coupons;
}