package com.org.composter.dao;

import com.org.composter.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerDao extends JpaRepository<Seller, Long> {
    Optional<Seller> findByContact(String userContact);
}
