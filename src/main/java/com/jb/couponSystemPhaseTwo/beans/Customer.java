package com.jb.couponSystemPhaseTwo.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generated value per entity
    private int id;
    @Column(length = 40, nullable = false)
    private String firstName;
    @Column(length = 40, nullable = false)
    private String lastName;
    @Column(length = 40, nullable = false)
    private String email;
    @Column(length = 40, nullable = false)
    private String password;
    @ManyToMany
    @JsonIgnore
    @ToString.Exclude
    @JoinTable(name = "customers_coupons", joinColumns = {
            @JoinColumn(name = "customer_id") }, inverseJoinColumns = {
            @JoinColumn(name = "coupons_id") })
    private List<Coupon> coupons;
}
