package com.zen.controller;

import com.zen.model.Product;
import com.zen.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/game")
public class GameController {
    private final ProductRepository productRepository;

    public GameController( ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping("/category")
    public List<String> getCategories(){
        List<String> categories= productRepository. findAllCategories();

      return categories;

    }
}
