package com.org.composter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "items")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ItemId")
    private long itemId;

    @Column(name = "SelleId")
    private int sellerId;

    @Column(name = "ItemName")
    private String itemName;

    @Column(name = "ItemWeight")
    private String itemWeight;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "Image", columnDefinition = "BLOB NOT NULL")
    private byte[] image;

    @Column(name = "Cost")
    private long cost;

    @Column(name = "DayPosted")
    @Temporal(TemporalType.DATE)
    private Date dayPosted;
}
