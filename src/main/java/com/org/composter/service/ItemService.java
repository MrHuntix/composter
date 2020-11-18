package com.org.composter.service;

import com.org.composter.dao.ItemsDao;
import com.org.composter.dao.LogsDao;
import com.org.composter.dao.SellerDao;
import com.org.composter.model.Items;
import com.org.composter.model.Logs;
import com.org.composter.request.NewItemRequest;
import com.org.composter.response.ItemResponse;
import com.org.composter.util.MapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    private static final Logger LOG = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private SellerDao sellerDao;

    @Autowired
    private LogsDao logsDao;

    @Autowired
    private ItemsDao itemsDao;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public boolean add(NewItemRequest item) {
        LOG.info("adding new item {} to market", item.getItemname());
        Logs logs = MapperUtil.getLogs(item);
        logs = logsDao.saveAndFlush(logs);
        LOG.info("persisted logs record {}. Start of saving item", logs.getId());
        long sellerId = sellerDao.findByContact(item.getUserid()).get().getSellerId();
        LOG.info("found seller id {} for seller {}. Building item", sellerId, item.getUserid());
        Items items = MapperUtil.getItem(item, sellerId);
        LOG.info("persisting item to db");
        items = itemsDao.saveAndFlush(items);
        LOG.info("persisted item {}", items.getItemId());
        return true;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public String deleteItem(String id) {
        itemsDao.deleteById(Long.getLong(id));
        return "deleteditem";
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public List<Items> getItemsForUser(String user) {
        LOG.info("fetching items for {}", user);
        long sellerId = sellerDao.findByContact(user).get().getSellerId();
        LOG.info("found {} for  seller {}. Fetching items", sellerId, user);
        List<Items> items = itemsDao.getItemsBySellerId(String.valueOf(sellerId));
        LOG.info("found {} items for {}", items.size(), sellerId);
        return items;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void deleteZero() {
        itemsDao.deleteZeroValueItems();
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public List<ItemResponse> getAllItems() {
        LOG.info("start of fetch items process");
        List<Items> items = itemsDao.findAll();
        if(CollectionUtils.isEmpty(items)) {
            LOG.info("there are no items present");
            return new ArrayList<>();
        }
        LOG.info("found {} items", items.size());
        List<ItemResponse> response = MapperUtil.getMappedItemSet(items);
        LOG.info("mapped {} items", response.size());
        return response;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void updateItem(long id, String weight) {
        LOG.info("updating item {}", id);

        itemsDao.updateWeight(id, weight);
    }


}
