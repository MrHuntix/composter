package com.org.composter.dao;

import com.org.composter.model.Offers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffersDao extends JpaRepository<Offers, Long> {
}
