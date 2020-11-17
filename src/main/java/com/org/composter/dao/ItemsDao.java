package com.org.composter.dao;

import com.org.composter.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemsDao extends JpaRepository<Items, Long> {
    List<Items> getItemsBySellerId(String sellerId);
    void deleteZeroValueItems();
    void updateWeight(long id, String weight);
}
