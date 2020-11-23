package com.org.composter.dao;

import com.org.composter.model.Items;
import com.org.composter.response.AllItemsResponse;
import com.org.composter.response.ViewOffersResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsDao extends JpaRepository<Items, Long> {
    List<Items> getItemsBySellerId(String sellerId);

    @Modifying
    void deleteZeroValueItems();

    @Modifying
    void updateWeight(long id, String weight);

}
