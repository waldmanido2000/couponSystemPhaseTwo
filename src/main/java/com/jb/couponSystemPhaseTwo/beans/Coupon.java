package com.jb.couponSystemPhaseTwo.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "coupons")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generated value per entity
    private int id;
    @Column(length = 40, nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(length = 40, nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Date startDate;
    @Column(nullable = false)
    private Date endDate;
    @Column(nullable = false)
    private int amount;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private String image;
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private Company company;

    @ManyToMany(mappedBy = "coupons")
    @ToString.Exclude
    @JsonIgnore
    private List<Customer> customers;
}
