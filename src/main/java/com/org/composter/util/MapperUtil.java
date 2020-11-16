package com.org.composter.util;

import com.org.composter.model.Buyer;
import com.org.composter.model.Seller;
import com.org.composter.request.LoginRequest;
import com.org.composter.request.RegisterRequest;

public class MapperUtil {
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
}
