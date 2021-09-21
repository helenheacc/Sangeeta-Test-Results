package com.springboot.CBAspringbootapp.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.springframework.stereotype.Component;

import com.springboot.CBAspringbootapp.bean.Product;

@Component
public class ProductService {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    Product[] products = {
            new Product(1, "Apple", "iPhone X", 2000),
            new Product(2, "Samsung", "Note 9", 1400),
            new Product(3, "Google", "Pixel 3", 1309),
            new Product(4, "OnePlus", "6T", 788),
    };
    public Future<List<Product>> getProducts() {       

        return executorService.submit(()-> {
            boolean shouldThrowError = Math.random() <= 0.2;
            if (shouldThrowError) {
                throw new RuntimeException("An unknown error occurred");
            }
            Thread.sleep((int)(Math.random() * 2000));
            return List.of(products);
        });
    }
    
    public Optional<Product> getProductById(int id) {
    	return List.of(products).stream().filter(product -> product.getId() == id)
				.findFirst();	
    }
      
}

