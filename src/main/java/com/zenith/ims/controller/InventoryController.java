package com.zenith.ims.controller;

import com.zenith.ims.dto.InventoryItemRequest; // <-- Import the new DTO
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
        List<InventoryItem> items = inventoryItemRepository.findByCompanyId(userPrincipal.getCompanyId());
        return ResponseEntity.ok(items);
    }
    @PostMapping
    public ResponseEntity<InventoryItem> createItem(@AuthenticationPrincipal UserPrincipal userPrincipal, 
                                                    @Valid @RequestBody InventoryItemRequest itemRequest) {
        
        // 1. Convert the DTO to our database entity
        InventoryItem newItem = itemRequest.toInventoryItem();

        // 2. Set the secure, server-side fields
        newItem.setCompanyId(userPrincipal.getCompanyId());
        newItem.setLastUpdatedBy(userPrincipal.getUser()); 

        // 3. Save the complete and valid entity
        InventoryItem savedItem = inventoryItemRepository.save(newItem);
        return ResponseEntity.ok(savedItem);
    }
}