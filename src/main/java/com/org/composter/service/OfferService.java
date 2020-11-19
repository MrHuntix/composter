package com.org.composter.service;

import com.org.composter.dao.BuyerDao;
import com.org.composter.dao.OffersDao;
import com.org.composter.dao.SellerDao;
import com.org.composter.model.Buyer;
import com.org.composter.model.Offers;
import com.org.composter.model.Seller;
import com.org.composter.request.OfferRequest;
import com.org.composter.response.CartReponse;
import com.org.composter.response.ViewOffersResponse;
import com.org.composter.util.MapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OfferService {
    private static final Logger LOG = LoggerFactory.getLogger(OfferService.class);

    @Autowired
    private OffersDao offersDao;

    @Autowired
    private SellerDao sellerDao;

    @Autowired
    private BuyerDao buyerDao;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public List<HashMap<String, String>> getOffersForSeller(String seller) {
        LOG.info("getting offers for seller {}", seller);
        Seller s = sellerDao.findByContact(seller).get();
        LOG.info("fetching offers for seller {} for {}", s.getSellerId(), seller);
        List<ViewOffersResponse> offers = offersDao.getOffersForSeller(String.valueOf(s.getSellerId()));
        List<HashMap<String, String>> mappedOffers = offers.stream().map(offer -> {
            HashMap<String, String> items = new HashMap<>();
            items.put("OfferId", String.valueOf(offer.getOfferId()));
            items.put("ItemName", offer.getItemName());
            items.put("ItemWeight", offer.getItemWeight());
            items.put("Cost", String.valueOf(offer.getItemCost()));
            items.put("BuyerName", offer.getBuyerName());
            items.put("BuyerContact", offer.getBuyerContact());
            items.put("weight", offer.getWeight());
            items.put("cost", offer.getOfferCost());
            return items;
        }).collect(Collectors.toList());
        LOG.info("found and mapped {} offers for {}", mappedOffers.size(), seller);
        return mappedOffers;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public boolean placeOffer(OfferRequest offerRequest) {
        LOG.info("placing offer for {} by {}", offerRequest.getItemId(), offerRequest.getBuyerContact());
        LOG.info("getting seller {}", offerRequest.getSellerContact());
        Seller seller = sellerDao.findByContact(offerRequest.getSellerContact()).get();
        LOG.info("got seller {} for {}", seller.getSellerId(), seller.getContact());
        LOG.info("getting buyer {}", offerRequest.getSellerContact());
        Buyer buyer = buyerDao.findByContact(offerRequest.getSellerContact()).get();
        LOG.info("got buyer {} for {}", buyer.getId(), buyer.getContact());
        LOG.info("building offer");
        Offers offer = MapperUtil.getOffer(offerRequest, buyer.getId(), seller.getSellerId());
        try {
            offer = offersDao.saveAndFlush(offer);
            LOG.info("placed offer {}", offer.getOfferId());
            return true;
        } catch (Exception e) {
            LOG.info("exception while placing offer");
            e.printStackTrace();
            return false;
        }
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public List<Map<String, String>> getCart(String id) {
        LOG.info("getting cart for {}", id);
        Buyer buyer = buyerDao.findByContact(id).get();
        LOG.info("got {} for {}", buyer.getId(), id);
        List<CartReponse> cart = offersDao.getCart(String.valueOf(buyer.getId()));
        List<Map<String, String>> mappedCart = cart.stream().map(c -> {
            HashMap<String, String> item = new HashMap<>();
            item.put("compostNameCart", c.getItemName());
            item.put("compostCostCart", c.getCost());
            item.put("compostWeightCart", c.getWeight());
            return item;
        }).collect(Collectors.toList());
        LOG.info("found {} in cart", cart.size());
        return mappedCart;
    }
}
