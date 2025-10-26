package com.zenith.ims.controller;

import com.zenith.ims.model.InventoryItem;
import com.zenith.ims.repository.InventoryItemRepository;
import com.zenith.ims.security.UserPrincipal;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryItemRepository inventoryItemRepository;

    public InventoryController(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }
    @GetMapping
    public ResponseEntity<List<InventoryItem>> getInventory(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        // Find all items that match the companyId of the logged-in user
        List<InventoryItem> items = inventoryItemRepository.findByCompanyId(userPrincipal.getCompanyId());
        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<InventoryItem> createItem(@AuthenticationPrincipal UserPrincipal userPrincipal, @Valid @RequestBody InventoryItem item) {
        // Set the companyId and user from the authenticated principal
        item.setCompanyId(userPrincipal.getCompanyId());
        
        // Set the user who created/updated this item
        item.setLastUpdatedBy(userPrincipal.getUser()); 

        InventoryItem savedItem = inventoryItemRepository.save(item);
        return ResponseEntity.ok(savedItem);
    }
}