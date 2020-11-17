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
@SqlResultSetMapping(name="updateResult", columns = { @ColumnResult(name = "count")})
@NamedNativeQueries({
        @NamedNativeQuery(name = "Items.getItemsBySellerId", query = Items.getItemsBySellerId, resultClass = Items.class),
        @NamedNativeQuery(name = "Items.deleteZeroValueItems", query = Items.deleteZeroValueItems, resultSetMapping = "updateResult")
        @NamedNativeQuery(name = "Items.updateWeight", query = Items.updateWeight, resultSetMapping = "updateResult")
})
public class Items {
    public static final String getItemsBySellerId = "SELECT * FROM items WHERE SelleId= :sellerId";
    public static final String deleteZeroValueItems = "DELETE FROM items WHERE ItemWeight=0";
    public static final String updateWeight = "UPDATE items SET ItemWeight= :weight WHERE ItemId= :id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ItemId")
    private long itemId;

    @Column(name = "SelleId")
    private long sellerId;

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
