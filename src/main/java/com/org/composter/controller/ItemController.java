package com.org.composter.controller;

import com.org.composter.model.Items;
import com.org.composter.request.NewItemRequest;
import com.org.composter.response.ItemResponse;
import com.org.composter.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {
    private static final Logger LOG = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    @GetMapping(name = "/item/{user}", produces = "application/json")
    ResponseEntity<List<Items>> items(@PathVariable("user") String user) {
        LOG.info("fetching items for {}", user);
        return ResponseEntity.ok(itemService.getItems(user));
    }

    @GetMapping(name = "/items", produces = "application/json")
    ResponseEntity<List<ItemResponse>> getItems() {
        LOG.info("start of fetching items");
        List<ItemResponse> response = itemService.getItems();
        return ResponseEntity.ok(response);
    }

    @PostMapping(name = "/item", consumes = "application/json", produces = "application/json")
    ResponseEntity<String> add(@RequestBody NewItemRequest item) {
        LOG.info("start of request to add item");
        boolean response = itemService.add(item);
        return response?ResponseEntity.ok("success"):ResponseEntity.ok("unsuccess");
    }

    @PostMapping(name = "/item/{id}/{weight}")
    ResponseEntity<String> updateItem(@PathVariable("id") String id, @PathVariable("weight") String weight) {
        itemService.updateItem(Long.parseLong(id), weight);
        return ResponseEntity.ok("updated");
    }

    @DeleteMapping(name = "/item/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable("id") String id) {
        LOG.info("deleting item {}", id);
        return ResponseEntity.ok(itemService.deleteItem(id));
    }

    @DeleteMapping(name="/zero")
    public ResponseEntity<String> deleteZero() {
        itemService.deleteZero();
        return ResponseEntity.ok("deleted");
    }
}
