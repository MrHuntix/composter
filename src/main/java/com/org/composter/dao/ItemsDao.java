package com.org.composter.dao;

import com.org.composter.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsDao extends JpaRepository<Items, Long> {
}
