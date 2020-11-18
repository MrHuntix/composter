package com.org.composter.controller;

import com.org.composter.model.Items;
import com.org.composter.request.NewItemRequest;
import com.org.composter.response.ItemResponse;
import com.org.composter.response.SimpleResponse;
import com.org.composter.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemsController {
    private static final Logger LOG = LoggerFactory.getLogger(ItemsController.class);

    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/item/seller/{sellerContact}", produces = "application/json")
    ResponseEntity<List<Items>> reqItemForUser(@PathVariable("sellerContact") String sellerContact) {
        LOG.info("fetching items for {}", sellerContact);
        List<Items> response = itemService.getItemsForUser(sellerContact);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/item/all" , produces = "application/json")
    ResponseEntity<List<ItemResponse>> reqAllItems() {
        LOG.info("start of fetching items");
        List<ItemResponse> response = itemService.getAllItems();
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/item/add")
    ResponseEntity<SimpleResponse> reqAddNewItem(@RequestBody NewItemRequest item) {
        LOG.info("start of request to add item");
        boolean response = itemService.add(item);
        return response?ResponseEntity.ok(new SimpleResponse("success")):ResponseEntity.ok(new SimpleResponse("unsuccess"));
    }

    @PutMapping(value = "/item/{id}/weight/{weight}")
    ResponseEntity<SimpleResponse> reqUpdateItem(@PathVariable("id") String id, @PathVariable("weight") String weight) {
        itemService.updateItem(Long.parseLong(id), weight);
        return ResponseEntity.ok(new SimpleResponse("updated"));
    }

    @DeleteMapping(value = "/item/{id}")
    public ResponseEntity<SimpleResponse> reqDeleteItemById(@PathVariable("id") String id) {
        LOG.info("deleting item {}", id);
        return ResponseEntity.ok(new SimpleResponse(itemService.deleteItem(id)));
    }

    @DeleteMapping(value = "/item/zero")
    public ResponseEntity<SimpleResponse> reqDeleteZeroItem() {
        itemService.deleteZero();
        return ResponseEntity.ok(new SimpleResponse("deleted"));
    }
}
