package com.org.composter.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferRequest {
    private String itemId;
    private String buyerContact;
    private String sellerContact;
    private String weight;
    private String cost;
}
