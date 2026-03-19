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

    @Query(value = "SELECT DISTINCT ON (category, subcategory) * FROM product ORDER BY category, subcategory, id", nativeQuery = true)
    List<Product> findDistinctByCategoryAndSubcategory();

    @Query(value = "SELECT * FROM product WHERE category = ?1 AND subcategory = ?2 ORDER BY RANDOM() LIMIT 2", nativeQuery = true)
    List<Product> findTwoRandomByCategoryAndSubcategory(String category, String subcategory);

    }





