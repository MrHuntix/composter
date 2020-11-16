package com.org.composter.dao;

import com.org.composter.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerDao extends JpaRepository<Buyer, Long> {
}
