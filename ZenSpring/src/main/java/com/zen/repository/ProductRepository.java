package com.zen.repository;

import com.zen.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.Period;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findByCategory(String category);
    List<Product> findByCategoryAndSubcategory(String category, String subcategory);
    List<Product> findByColor(String color);
    List<Product> findByPriceGreaterThan(BigDecimal price);

    List<Product> findByCategoryAndColorOrderByPriceDesc(String category,String color);
    List<Product> findByNameIgnoreCaseContaining(String name);
    long countByCategory(String category);

    @Query("select distinct category from Product")
    List<String> findAllCategories();
    }

