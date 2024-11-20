package com.lb8.rest_api.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lb8.rest_api.persistence.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private static final Map<Long, Product> ProductMap = new HashMap<>();

    public void create(Product product) {
        final Long clientId = Instant.now().getEpochSecond();
        product.setId(clientId); 
        ProductMap.put(clientId, product);
    }

    public List<Product> readAll() {
        return new ArrayList<>(ProductMap.values());
    }

    public Product read(Long id) {
        return ProductMap.get(id);
    }

    public boolean update(Product product, Long id) {
        if (ProductMap.containsKey(id)) {
            product.setId(id);
            ProductMap.put(id, product);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        return ProductMap.remove(id) != null;
    }
}
