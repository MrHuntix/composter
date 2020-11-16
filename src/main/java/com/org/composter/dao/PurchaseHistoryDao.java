package com.org.composter.dao;

import com.org.composter.model.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseHistoryDao extends JpaRepository<PurchaseHistory, Long> {
}
