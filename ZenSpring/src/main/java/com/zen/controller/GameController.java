package com.zen.controller;

import com.zen.model.Product;
import com.zen.repository.ProductRepository;
import com.zen.service.gameService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api/game")
public class GameController {

    private final gameService gameService;

    public GameController( gameService gameService){
        this.gameService = gameService;
    }

    @GetMapping("/category")
    public List<String> getCategories(){
        List<String> categories = gameService.getCategories();
      return categories;

    }

    @GetMapping("/categoryCard")
    public List<Product> getCategoryCards(){
        List<Product> products = gameService.getCategoryCards();
        return products;
    }

    @GetMapping("/getCards")
    public List<Product> getCardsForGame(@RequestParam String category,@RequestParam String subcategory) {
        List<Product> products = gameService.getCardsForGame(category,subcategory );
        return products;
    }

    @GetMapping("/game/battle-products")
    public List<Product> getBattleProducts(
            @RequestParam String category,
            @RequestParam String subcategory
    ) {
        return gameService.getTwoRandomProducts(category, subcategory);
    }

}
