package org.example.warehouse;

import java.math.BigDecimal;
import java.util.UUID;
public class ProductRecord {
    private final UUID uuid;
    private final String name;
    private final Category category;
    private BigDecimal price;

    public ProductRecord(UUID uuid, String name, Category category, BigDecimal price) {

        if (uuid == null) this.uuid = UUID.randomUUID();
        else this.uuid = uuid;
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

    public Category category() {
        return this.category;
    }

    public BigDecimal price() {
        return this.price;
    }
}