package com.zen.service;

import com.zen.model.Product;
import com.zen.repository.ProductRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class gameService {

    ProductRepository productRepository;

    public gameService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<String> getCategories(){
        List<String> categories = productRepository.findAllCategories();
        return categories;
    }

    public List<Product> getCategoryCards (){
        List<Product> products = productRepository.findDistinctByCategoryAndSubcategory();
        return products;
    }

    public List<Product> getCardsForGame(String category,String subcategory){
        List<Product> products = productRepository.findByCategoryAndSubcategory(category,subcategory);
        return products;
    }

    public List<Product> getTwoRandomProducts(String category, String subcategory) {
        List<Product> allProducts = productRepository.findByCategoryAndSubcategory(category, subcategory);
        if (allProducts.size() <= 2) {
            return allProducts;
        }
        Collections.shuffle(allProducts);
        return allProducts.subList(0, 2);
    }
}
