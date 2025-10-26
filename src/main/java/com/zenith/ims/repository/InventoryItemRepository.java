package com.zenith.ims.repository;

import com.zenith.ims.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    // Spring Data JPA will automatically create a query for this method:
    // "SELECT * FROM inventory_items WHERE company_id = ?"
    List<InventoryItem> findByCompanyId(String companyId);
}