package com.org.composter.service;

import com.org.composter.dao.ItemsDao;
import com.org.composter.dao.LogsDao;
import com.org.composter.dao.SellerDao;
import com.org.composter.model.Items;
import com.org.composter.model.Logs;
import com.org.composter.model.Seller;
import com.org.composter.request.NewItemRequest;
import com.org.composter.response.ItemResponse;
import com.org.composter.util.MapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private static final Logger LOG = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private SellerDao sellerDao;

    @Autowired
    private LogsDao logsDao;

    @Autowired
    private ItemsDao itemsDao;

    @Autowired
    private MapperUtil mapperUtil;

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public boolean add(NewItemRequest item) {
        LOG.info("adding new item {} to market", item.getItemname());
        Logs logs = mapperUtil.getLogs(item);
        logs = logsDao.saveAndFlush(logs);
        LOG.info("persisted logs record {}. Start of saving item", logs.getId());
        long sellerId = sellerDao.findByContact(item.getUserid()).get().getSellerId();
        LOG.info("found seller id {} for seller {}. Building item", sellerId, item.getUserid());
        Items items = mapperUtil.getItem(item, sellerId);
        LOG.info("persisting item to db");
        items = itemsDao.saveAndFlush(items);
        LOG.info("persisted item {}", items.getItemId());
        return true;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public String deleteItem(String id) {
        LOG.info("deleting {}", Long.parseLong(id));
        itemsDao.deleteById(Long.parseLong(id));
        return "deleteditem";
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public List<HashMap<String, String>> getItemsForUser(String user) {
        LOG.info("fetching items for {}", user);
        long sellerId = sellerDao.findByContact(user).get().getSellerId();
        LOG.info("found {} for  seller {}. Fetching items", sellerId, user);
        List<Items> items = itemsDao.getItemsBySellerId(String.valueOf(sellerId));
        List<HashMap<String, String>> itemsmapped = items.stream().map(item -> {
            HashMap<String, String> i = new HashMap<>();
            i.put("ItemId", String.valueOf(item.getItemId()));
            i.put("Cost", String.valueOf(item.getCost()));
            i.put("ItemName", item.getItemName());
            i.put("ItemWeight", item.getItemWeight());
            return i;
        }).collect(Collectors.toList());
        LOG.info("found {} items for {}", itemsmapped.size(), sellerId);
        return itemsmapped;
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
        List<ItemResponse> response = mapperUtil.getMappedItemSet(items);
        LOG.info("mapped {} items", response.size());
        return response;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void updateItem(long id, String weight) {
        LOG.info("updating item {}", id);
        itemsDao.updateWeight(id, weight);
    }

    public List<Map<String, String>> dispItems() {
        List<Items> items = itemsDao.findAll();
        LOG.info("found {}", items.size());
        List<Map<String, String>> mappedItems = items.stream().map(item -> {
            Seller seller = sellerDao.findById(item.getSellerId()).get();
            Map<String, String> i = new HashMap<>();
            i.put("ItemId", String.valueOf(item.getItemId()));
            i.put("Name", seller.getName());
            i.put("Contact", seller.getContact());
            i.put("ItemName", item.getItemName());
            i.put("Cost", String.valueOf(item.getCost()));
            i.put("DayPosted", formatter.format(item.getDayPosted()));
            i.put("ItemWeight", item.getItemWeight());
            i.put("Image", Base64.getEncoder().encodeToString(item.getImage()));
            return i;
        }).collect(Collectors.toList());
        return mappedItems;
    }


}
