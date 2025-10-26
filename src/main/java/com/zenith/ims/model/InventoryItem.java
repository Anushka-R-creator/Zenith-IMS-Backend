package com.zenith.ims.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "inventory_items")
@Data
@NoArgsConstructor
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    private String description;

    @NotBlank
    @Column(nullable = false)
    private String category;

    @NotNull
    @Min(0)
    private Integer quantity;

    @NotBlank
    private String location;

    private String supplier;

    private LocalDate purchaseDate;

    private LocalDate warrantyEndDate;

    private LocalDate expiryDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    @NotNull
    @Column(nullable = false)
    private String companyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User lastUpdatedBy;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    public enum ItemStatus {
        AVAILABLE,
        IN_USE,
        DAMAGED,
        DISPOSED
    }
}