package com.org.composter.model;

import com.org.composter.response.CartReponse;
import com.org.composter.response.ViewOffersResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "offers")
@SqlResultSetMapping(name = "Offers.getOffersForSellerMapping", classes = @ConstructorResult(targetClass = ViewOffersResponse.class, columns = {
        @ColumnResult(name = "offerId", type = Long.class), @ColumnResult(name = "itemName", type = String.class),
        @ColumnResult(name = "itemWeight", type = String.class), @ColumnResult(name = "itemCost", type = Long.class),
        @ColumnResult(name = "buyerName", type = String.class), @ColumnResult(name = "buyerContact", type = String.class),
        @ColumnResult(name = "weight", type = String.class), @ColumnResult(name = "offerCost", type = String.class),
}))

@SqlResultSetMapping(name = "Offers.getCartMapping", classes = @ConstructorResult(targetClass = CartReponse.class, columns = {
        @ColumnResult(name = "itemName", type = String.class), @ColumnResult(name = "weight", type = String.class),
        @ColumnResult(name = "cost", type = String.class)
}))

@NamedNativeQueries({
        @NamedNativeQuery(name = "Offers.getOffersForSeller", query = Offers.getOffersForSeller, resultSetMapping = "Offers.getOffersForSellerMapping"),
        @NamedNativeQuery(name = "Offers.getCart", query = Offers.getCart, resultSetMapping = "Offers.getCartMapping"),
        @NamedNativeQuery(name = "Offers.getOfferByItemAndBuyerAndSeller", query = Offers.getOfferByItemAndBuyerAndSeller, resultClass = Offers.class)
        })
public class Offers {
    public static final String getOffersForSeller = "SELECT offers.OfferId offerId,items.ItemName itemName,items.ItemWeight itemWeight,items.Cost itemCost,buyer.Name buyerName,buyer.Contact buyerContact,offers.weight weight,offers.cost offerCost FROM offers JOIN items JOIN buyer WHERE offers.ItemId=items.ItemId AND offers.SellerId=:seller AND offers.BuyerId=buyer.id";
    public static final String getCart = "SELECT items.ItemName itemName,offers.weight weight,offers.cost cost FROM items JOIN offers where offers.ItemId=items.ItemId AND offers.BuyerId=:id";
    public static final String getOfferByItemAndBuyerAndSeller = "SELECT * from offers where ItemId=:itemId AND SellerId=:sellerId AND BuyerId=:buyerId";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OfferId")
    private long offerId;

    @Column(name = "ItemId")
    private long itemId;

    @Column(name = "SellerId")
    private long sellerId;

    @Column(name = "BuyerId")
    private long buyerId;

    @Column(name = "weight")
    private String weight;

    @Column(name = "cost")
    private String cost;
}
