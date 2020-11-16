package com.org.composter.dao;

import com.org.composter.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsDao extends JpaRepository<Items, Long> {
}
