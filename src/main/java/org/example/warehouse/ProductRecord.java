package org.example.warehouse;

import java.math.BigDecimal;
import java.util.UUID;
public class ProductRecord {
    private final UUID uuid;
    private final String name;
    private final Category category;
    private BigDecimal price;

    public ProductRecord(UUID id, String name, Category category, BigDecimal price) {

        if (id == null) this.uuid = UUID.randomUUID();
        else this.uuid = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public UUID uuid() {
        return this.uuid;
    }

    public String getName() {
        return name;
    }

    public Category category() {
        return this.category;
    }

    public BigDecimal price() {
        return this.price;
    }
}