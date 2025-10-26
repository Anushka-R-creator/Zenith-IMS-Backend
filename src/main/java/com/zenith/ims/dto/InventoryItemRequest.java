package com.zenith.ims.dto;

import com.zenith.ims.model.InventoryItem;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
public record InventoryItemRequest(
    @NotBlank
    String name,

    String description,

    @NotBlank
    String category,

    @NotNull
    @Min(0)
    Integer quantity,

    @NotBlank
    String location,

    String supplier,
    LocalDate purchaseDate,
    LocalDate warrantyEndDate,
    LocalDate expiryDate,

    @NotNull
    InventoryItem.ItemStatus status
) {
   
    public InventoryItem toInventoryItem() {
        InventoryItem item = new InventoryItem();
        item.setName(this.name);
        item.setDescription(this.description);
        item.setCategory(this.category);
        item.setQuantity(this.quantity);
        item.setLocation(this.location);
        item.setSupplier(this.supplier);
        item.setPurchaseDate(this.purchaseDate);
        item.setWarrantyEndDate(this.warrantyEndDate);
        item.setExpiryDate(this.expiryDate);
        item.setStatus(this.status);
        return item;
    }
}
