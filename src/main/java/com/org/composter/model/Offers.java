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
        @ColumnResult(name = "oid", type = Long.class), @ColumnResult(name = "iname", type = String.class),
        @ColumnResult(name = "iweight", type = String.class), @ColumnResult(name = "icost", type = Long.class),
        @ColumnResult(name = "bname", type = String.class), @ColumnResult(name = "bcontact", type = String.class),
        @ColumnResult(name = "oweight", type = String.class), @ColumnResult(name = "ocost", type = String.class),
}))

@SqlResultSetMapping(name = "Offers.getCartMapping", classes = @ConstructorResult(targetClass = CartReponse.class, columns = {
        @ColumnResult(name = "iname", type = String.class), @ColumnResult(name = "oweight", type = String.class),
        @ColumnResult(name = "ocost", type = String.class)
}))

@NamedNativeQueries({
        @NamedNativeQuery(name = "Offers.getOffersForSeller", query = Offers.getOffersForSeller, resultSetMapping = "Offers.getOffersForSellerMapping"),
        @NamedNativeQuery(name = "Offers.getCart", query = Offers.getCart, resultSetMapping = "Offers.getCartMapping")
        })
public class Offers {
    public static final String getOffersForSeller = "SELECT offers.OfferId oid,items.ItemName iname,items.ItemWeight iweight,items.Cost icost,buyer.Name bname,buyer.Contact bcontact,offers.weight oweight,offers.cost ocost FROM offers JOIN items JOIN buyer WHERE offers.ItemId=items.ItemId AND offers.SellerId=:seller AND offers.BuyerId=buyer.id";
    public static final String getCart = "SELECT items.ItemName iname,offers.weight oweight,offers.cost ocost FROM items JOIN offers where offers.ItemId=items.ItemId AND offers.BuyerId=:id";

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
