package com.org.composter.dao;

import com.org.composter.model.Offers;
import com.org.composter.response.CartReponse;
import com.org.composter.response.ViewOffersResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OffersDao extends JpaRepository<Offers, Long> {
    @Query(nativeQuery=true)
    List<ViewOffersResponse> getOffersForSeller(String seller);
    @Query(nativeQuery=true)
    List<CartReponse> getCart(String id);
    @Query(nativeQuery=true)
    Optional<Offers> getOfferByItemAndBuyerAndSeller(String itemId, long sellerId, long buyerId);
}
