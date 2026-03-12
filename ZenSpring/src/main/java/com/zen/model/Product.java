package com.zen.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(nullable = false)
    String name;
    @Column(nullable = false,scale = 2,precision = 10)
    BigDecimal price;
    @Column(nullable = false,length = 1000)
    String link;
    @Column(name = "img_url" ,nullable = false,length = 1000)
    String imgUrl;
    @Column(nullable = false,length = 100)
    String color;
    @Column(nullable = false,length = 255)
    String category;
    @Column(nullable = false,length = 255)
    String subcategory;

    public Product() {
    }

}
