package com.cdac.multilayerDI.repository;



import com.cdac.multilayerDI.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {

    private List<Product> products = List.of(
            new Product(1, "Laptop", 89990, "Electronics"),
            new Product(2, "Phone", 7900000, "Electronics"),
            new Product(3, "Shoes", 98000, "Fashion"),
            new Product(4, "Watch", 78900, "Accessories"),
            new Product(5, "Bag", 19000, "Fashion")
    );

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }
}