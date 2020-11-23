package com.org.composter.model;

import com.org.composter.response.AllItemsResponse;
import com.org.composter.response.ViewOffersResponse;
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
@SqlResultSetMapping(name="updateResult", classes = { @ConstructorResult(targetClass = Integer.class, columns = {
        @ColumnResult(name = "value", type = Integer.class)
})})

@SqlResultSetMapping(name = "Items.getItemsForSellerMapper", classes = @ConstructorResult(targetClass = AllItemsResponse.class, columns = {
        @ColumnResult(name = "iid", type = Long.class), @ColumnResult(name = "sname", type = String.class),
        @ColumnResult(name = "scontact", type = String.class), @ColumnResult(name = "iname", type = Long.class),
        @ColumnResult(name = "icost", type = String.class), @ColumnResult(name = "idp", type = String.class),
        @ColumnResult(name = "iweight", type = String.class), @ColumnResult(name = "iimg", type = String.class),
}))

@NamedNativeQueries({
        @NamedNativeQuery(name = "Items.getItemsBySellerId", query = Items.getItemsBySellerId, resultClass = Items.class),
        @NamedNativeQuery(name = "Items.deleteZeroValueItems", query = Items.deleteZeroValueItems, resultSetMapping = "updateResult"),
        @NamedNativeQuery(name = "Items.updateWeight", query = Items.updateWeight, resultSetMapping = "updateResult"),
        @NamedNativeQuery(name = "Items.getItemsForSeller", query = Items.getItemsForSeller, resultSetMapping = "Items.getItemsForSellerMapper")
})
public class Items {
    public static final String getItemsBySellerId = "SELECT * FROM items WHERE SelleId= :sellerId";
    public static final String deleteZeroValueItems = "DELETE FROM items WHERE ItemWeight=0";
    public static final String updateWeight = "UPDATE items SET ItemWeight= :weight WHERE ItemId= :id";
    public static final String getItemsForSeller = "SELECT items.ItemId iid,seller.Name sname,seller.Contact scontact,items.ItemName iname,items.Cost icost,items.DayPosted idp,items.ItemWeight iweight,items.Image iimg FROM items JOIN seller WHERE items.SelleId=seller.SellerId ORDER BY items.ItemId DESC";

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
