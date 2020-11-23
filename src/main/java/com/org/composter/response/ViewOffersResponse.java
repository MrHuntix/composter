package com.org.composter.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class ViewOffersResponse {
    private Long offerId;
    private String itemName;
    private String itemWeight;
    private Long itemCost;
    private String buyerName;
    private String buyerContact;
    private String weight;
    private String offerCost;

    public ViewOffersResponse(Long offerId, String itemName, String itemWeight, Long itemCost, String buyerName, String buyerContact, String weight, String offerCost) {
        this.offerId = offerId;
        this.itemName = itemName;
        this.itemWeight = itemWeight;
        this.itemCost = itemCost;
        this.buyerName = buyerName;
        this.buyerContact = buyerContact;
        this.weight = weight;
        this.offerCost = offerCost;
    }
}
