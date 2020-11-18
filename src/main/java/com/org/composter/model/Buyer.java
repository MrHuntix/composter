package com.org.composter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "buyer")
@NamedNativeQueries({
        @NamedNativeQuery(name = "Buyer.findByContact", query = Buyer.findByContact, resultClass = Buyer.class)
})
public class Buyer {
    public static final String findByContact = "SELECT * FROM buyer WHERE Contact=:userContact";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Contact")
    private String contact;

    @Column(name = "Password")
    private String password;
}
