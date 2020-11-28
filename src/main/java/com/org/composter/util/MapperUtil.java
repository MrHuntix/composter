package com.org.composter.util;

import com.org.composter.dao.SellerDao;
import com.org.composter.model.*;
import com.org.composter.request.NewItemRequest;
import com.org.composter.request.OfferRequest;
import com.org.composter.request.RegisterRequest;
import com.org.composter.response.ItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperUtil {
    @Autowired
    private SellerDao sellerDao;

    public Seller getSeller(RegisterRequest request) {
        Seller s =new Seller();
        s.setName(request.getUsername());
        s.setContact(request.getContact());
        s.setPassword(request.getPassword());
        return s;
    }

    public Buyer getBuyer(RegisterRequest request) {
        Buyer b =new Buyer();
        b.setName(request.getUsername());
        b.setContact(request.getContact());
        b.setPassword(request.getPassword());
        return b;
    }

    public Logs getLogs(NewItemRequest itemRequest) {
        Logs logs = new Logs();
        logs.setLat(itemRequest.getLat());
        logs.setLng(itemRequest.getLng());
        logs.setWeight(itemRequest.getItemweight());
        return logs;
    }

    public Items getItem(NewItemRequest itemRequest, long sellerId) {
        Items item = new Items();
        item.setSellerId(sellerId);
        item.setItemName(itemRequest.getItemname());
        item.setItemWeight(itemRequest.getItemweight());
        item.setImage(itemRequest.getImage().getBytes());
        item.setCost(itemRequest.getItemcost());
        item.setDayPosted(new Date(itemRequest.getDate()));
        return item;
    }

    public List<ItemResponse> getMappedItemSet(List<Items> items) {
        List<ItemResponse> responses = new ArrayList<>(items.size());
        responses = items.stream().map(item->{
             Seller seller = sellerDao.findById(item.getSellerId()).get();
            ItemResponse response = new ItemResponse(item, seller.getName(), seller.getContact());
            return response;
        }).collect(Collectors.toList());
        return responses;
    }

    public Offers getOffer(OfferRequest request, long buyerId, long sellerId) {
        Offers offers = new Offers();
        offers.setItemId(Long.parseLong(request.getItemId()));
        offers.setSellerId(sellerId);
        offers.setBuyerId(buyerId);
        offers.setWeight(request.getWeight());
        offers.setCost(request.getCost());
        return offers;
    }
}
