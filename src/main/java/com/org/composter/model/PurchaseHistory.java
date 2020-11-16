package com.org.composter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "purchasehistory")
public class PurchaseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PurchaseId")
    private long purchaseId;

    @Column(name = "BuyerId")
    private int buyerid;

    @Column(name = "ItemName")
    private String itemName;

    @Column(name = "ItemCost")
    private int itemCost;

    @Column(name = "ItemWeight")
    private String ItemWeight;

    @Column(name = "DayPosted")
    private String dayPosted;

    @Column(name = "DayPurchased")
    private String dayPurchased;

    @Column(name = "SellerId")
    private int sellerId;
}
