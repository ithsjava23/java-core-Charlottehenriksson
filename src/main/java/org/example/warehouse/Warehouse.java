package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
public class Warehouse {

    private static Warehouse instance;
    private final String name;
    private final Map<UUID, ProductRecord> productMap;
    private final List<ProductRecord> changedProducts;
    private final List<ProductRecord> addedProducts;


    private Warehouse(String name) {
        this.name = name;
        productMap = new HashMap<>();
        changedProducts = new ArrayList<>();
        addedProducts = new ArrayList<>();
    }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse("My store");
        }
        return instance;
    }

    public static Warehouse getInstance(String name) {
        return new Warehouse(name);
    }

    public boolean isEmpty() {
        return productMap.isEmpty();
    }

    public ProductRecord addProduct(UUID id, String name, Category category, BigDecimal price) {
        if (productMap.containsKey(id)) {
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Product name can't be null or empty.");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category can't be null.");
        }
        if (price == null) {
            price = BigDecimal.ZERO;
        }
        ProductRecord productRecord = new ProductRecord(id, name, category, price);
        addedProducts.add(productRecord);
        productMap.put(id, productRecord);
        return productRecord;
    }

    public void updateProductPrice(UUID id, BigDecimal newPrice) {
        if (!productMap.containsKey(id)) {
            throw new IllegalArgumentException("Product with that id doesn't exist.");
        }
        else {
            ProductRecord productRecord = productMap.get(id);
            productRecord.setPrice(newPrice);
            if (!changedProducts.contains(productRecord)) {
                changedProducts.add(productRecord);
            }
        }
    }

    public List<ProductRecord> getProducts() {
        return Collections.unmodifiableList(addedProducts);
    }

    public Optional<ProductRecord> getProductById(UUID id) {
        return Optional.ofNullable(productMap.get(id));
    }

    public List<ProductRecord> getChangedProducts() {
        return new ArrayList<>(changedProducts);
    }

    public Map<Category, List<ProductRecord>> getProductsGroupedByCategories() {
        return addedProducts.stream()
                .collect(Collectors.groupingBy(ProductRecord::category));
    }

    public List<ProductRecord> getProductsBy(Category category) {
        return addedProducts.stream()
                .filter(product -> product.category().equals(category))
                .collect(Collectors.toList());
    }

}
