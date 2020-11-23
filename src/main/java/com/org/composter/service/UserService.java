package com.org.composter.service;

import com.org.composter.dao.BuyerDao;
import com.org.composter.dao.SellerDao;
import com.org.composter.model.Buyer;
import com.org.composter.model.Seller;
import com.org.composter.request.LoginRequest;
import com.org.composter.request.RegisterRequest;
import com.org.composter.util.MapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private static final String BUYER = "buyer";
    private static final String SELLER = "seller";

    @Autowired
    private BuyerDao buyerDao;

    @Autowired
    private SellerDao sellerDao;

    /**
     * Validates data from @{@link LoginRequest} to the data in db
     * @param request login information of user.
     * @return true if credentials match false otherwise.
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public boolean login(LoginRequest request) {
        LOG.info("log in request for {}", request.getContact());
        if(BUYER.equals(request.getChoice())) {
            LOG.info("login request from {}", BUYER);
            Buyer buyer = buyerDao.findByContact(request.getContact()).orElse(null);
            if(Objects.isNull(buyer)) {
                LOG.info("no buyer exists for {}", request.getContact());
                return false;
            }
            if(request.getContact().equals(buyer.getContact()) && request.getPassword().equals(buyer.getPassword())) {
                LOG.info("login credentials valid for {}", request.getContact());
                return true;
            }

        } else if(SELLER.equals(request.getChoice())){
            LOG.info("login request from {}", SELLER);
            Seller seller = sellerDao.findByContact(request.getContact()).orElse(null);
            if(Objects.isNull(seller)) {
                LOG.info("no seller exists for {}", request.getContact());
                return false;
            }
            if(request.getContact().equals(seller.getContact()) && request.getPassword().equals(seller.getPassword())) {
                LOG.info("login credentials valid for {}", request.getContact());
                return true;
            }
        } else {
            LOG.info("invalid choice");
            return false;
        }
        LOG.info("invalid request user source. Got {}", request.getChoice());
        return false;
    }

    /**
     * adds a buyer
     * @param registerRequest registration information of buyer
     * @return true if buyer is inserted to db false if buyer already exists
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public boolean addBuyer(RegisterRequest registerRequest) {
        Buyer existingbuyer = buyerDao.findByContact(registerRequest.getContact()).orElse(null);
        if(Objects.nonNull(existingbuyer)) {
            LOG.info("buyer {} already exists", registerRequest.getContact());
            return false;
        }
        LOG.info("no buyer exists, creating new user {}", registerRequest.getContact());
        Buyer buyer = MapperUtil.getBuyer(registerRequest);
        buyerDao.saveAndFlush(buyer);
        return true;
    }

    /**
     * adds a seller
     * @param registerRequest registration information of seller
     * @return true if seller is inserted to db false if seller already exists
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public boolean addSeller(RegisterRequest registerRequest) {
        Seller existingseller = sellerDao.findByContact(registerRequest.getContact()).orElse(null);
        if(Objects.nonNull(existingseller)) {
            LOG.info("seller {} already exists", registerRequest.getContact());
            return false;
        }
        LOG.info("no seller exists, creating new user {}", registerRequest.getContact());
        Seller seller = MapperUtil.getSeller(registerRequest);
        sellerDao.saveAndFlush(seller);
        return true;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public String findSellerById(String id) {
        return sellerDao.findByContact(id).get().getName();
    }
}
