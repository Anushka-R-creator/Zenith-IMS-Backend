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

    /**
     * Gets all inventory items belonging to the currently logged-in user's company.
     * This endpoint is automatically secured by Spring Security.
     * @param userPrincipal The details of the authenticated user, injected by Spring.
     * @return A list of inventory items.
     */
    @GetMapping
    public ResponseEntity<List<InventoryItem>> getInventory(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        // Find all items that match the companyId of the logged-in user
        List<InventoryItem> items = inventoryItemRepository.findByCompanyId(userPrincipal.getCompanyId());
        return ResponseEntity.ok(items);
    }

    /**
     * Creates a new inventory item.
     * The item is automatically associated with the logged-in user's company.
     * @param userPrincipal The details of the authenticated user.
     * @param item The new item details from the request body.
     * @return The saved inventory item.
     */
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
