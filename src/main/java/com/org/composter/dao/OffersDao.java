package com.org.composter.dao;

import com.org.composter.model.Offers;
import com.org.composter.response.CartReponse;
import com.org.composter.response.ViewOffersResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OffersDao extends JpaRepository<Offers, Long> {
    List<ViewOffersResponse> getOffersForSeller(String seller);
    List<CartReponse> getCart(String id);
}
