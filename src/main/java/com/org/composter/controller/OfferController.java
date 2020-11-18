package com.org.composter.controller;

import com.org.composter.request.OfferRequest;
import com.org.composter.response.CartReponse;
import com.org.composter.response.ViewOffersResponse;
import com.org.composter.service.OfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OfferController {
    private static final Logger LOG = LoggerFactory.getLogger(OfferController.class);

    @Autowired
    private OfferService offerService;

    @GetMapping(value = "/offers/{seller}", produces = "application/json")
    public ResponseEntity<List<ViewOffersResponse>> getOffers(@PathVariable("seller") String seller) {
        LOG.info("getting offers for {}", seller);
        List<ViewOffersResponse> offers = offerService.getOffersForSeller(seller);
        return ResponseEntity.ok(offers);
    }

    @PostMapping(value = "/offers", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> placeOffer(@RequestBody OfferRequest offerRequest) {
        LOG.info("start of create offer request");
        boolean resposne = offerService.placeOffer(offerRequest);
        return resposne?ResponseEntity.ok("exists"):ResponseEntity.ok("offered");
    }

    @GetMapping(value = "/cart/{id}", produces = "application/json")
    public ResponseEntity<List<CartReponse>> getCart(@PathVariable("id")String id) {
        List<CartReponse> response = offerService.getCart(id);
        return ResponseEntity.ok(response);
    }
}
