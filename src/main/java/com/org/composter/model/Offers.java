package com.org.composter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "offers")
public class Offers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OfferId")
    private long offerId;

    @Column(name = "ItemId")
    private int itemId;

    @Column(name = "SellerId")
    private int sellerId;

    @Column(name = "BuyerId")
    private int buyerId;

    @Column(name = "weight")
    private String weight;

    @Column(name = "cost")
    private String cost;
}
