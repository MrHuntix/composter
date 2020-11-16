package com.org.composter.dao;

import com.org.composter.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerDao extends JpaRepository<Seller, Long> {
}
