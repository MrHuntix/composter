package com.org.composter.controller;

import com.org.composter.request.OfferRequest;
import com.org.composter.response.CartReponse;
import com.org.composter.response.SimpleResponse;
import com.org.composter.response.ViewOffersResponse;
import com.org.composter.service.OfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OfferController {
    private static final Logger LOG = LoggerFactory.getLogger(OfferController.class);

    @Autowired
    private OfferService offerService;

    @GetMapping(value = "/offers/{seller}", produces = "application/json")
    public ResponseEntity<List<HashMap<String, String>>> getOffers(@PathVariable("seller") String seller) {
        LOG.info("getting offers for {}", seller);
        List<HashMap<String, String>> offers = offerService.getOffersForSeller(seller);
        return ResponseEntity.ok(offers);
    }

    @PostMapping(value = "/offers", produces = "application/json", consumes = "application/json")
    public ResponseEntity<SimpleResponse> placeOffer(@RequestBody OfferRequest offerRequest) {
        LOG.info("start of create offer request");
        boolean resposne = offerService.placeOffer(offerRequest);
        return resposne?ResponseEntity.ok(new SimpleResponse("offered")):ResponseEntity.ok(new SimpleResponse("exists"));
    }

    @GetMapping(value = "/cart/{id}", produces = "application/json")
    public ResponseEntity<List<Map<String, String>>> getCart(@PathVariable("id")String id) {
        List<Map<String, String>> response = offerService.getCart(id);
        return ResponseEntity.ok(response);
    }
}
