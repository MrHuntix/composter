package com.org.composter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "seller")
@NamedNativeQueries({
        @NamedNativeQuery(name = "Seller.findByContact", query = Seller.findByContact, resultClass = Seller.class)
})
public class Seller {
    public static final String findByContact = "SELECT * FROM seller WHERE Contact=:userContact";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SellerId")
    private long sellerId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Contact")
    private String contact;

    @Column(name = "Password")
    private String password;
}
