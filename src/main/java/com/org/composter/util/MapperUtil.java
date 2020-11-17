package com.org.composter.util;

import com.org.composter.dao.SellerDao;
import com.org.composter.model.Buyer;
import com.org.composter.model.Items;
import com.org.composter.model.Logs;
import com.org.composter.model.Seller;
import com.org.composter.request.LoginRequest;
import com.org.composter.request.NewItemRequest;
import com.org.composter.request.RegisterRequest;
import com.org.composter.response.ItemResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MapperUtil {
    @Autowired
    private static SellerDao sellerDao;

    public static Seller getSeller(RegisterRequest request) {
        Seller s =new Seller();
        s.setName(request.getUsername());
        s.setContact(request.getContact());
        s.setPassword(request.getPassword());
        return s;
    }

    public static Buyer getBuyer(RegisterRequest request) {
        Buyer b =new Buyer();
        b.setName(request.getUsername());
        b.setContact(request.getContact());
        b.setPassword(request.getPassword());
        return b;
    }

    public static Logs getLogs(NewItemRequest itemRequest) {
        Logs logs = new Logs();
        logs.setLat(itemRequest.getLat());
        logs.setLng(itemRequest.getLng());
        logs.setWeight(itemRequest.getItemweight());
        return logs;
    }

    public static Items getItem(NewItemRequest itemRequest, long sellerId) {
        Items item = new Items();
        item.setSellerId(sellerId);
        item.setItemName(itemRequest.getItemname());
        item.setItemWeight(itemRequest.getItemweight());
        item.setImage(itemRequest.getImage());
        item.setCost(itemRequest.getItemcost());
        item.setDayPosted(itemRequest.getDate());
        return item;
    }

    public static List<ItemResponse> getMappedItemSet(List<Items> items) {
        List<ItemResponse> responses = new ArrayList<>();
        responses = items.stream().map(item->{
            Seller seller = sellerDao.findById(item.getSellerId()).get();
            ItemResponse response = new ItemResponse(item, seller.getName(), seller.getContact());
            return response;
        }).collect(Collectors.toList());
        return responses;
    }
}
