package com.zen.service;

import com.zen.model.Product;
import com.zen.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class gameService {

    ProductRepository productRepository;

    public gameService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getMerge(String category, String subcatagory){
        return mergeSort(getProductsForTasteBattle(category, subcatagory));
    }

    public List<Product> getProductsForTasteBattle(String category, String subcatagory){
        List<Product> products = productRepository.findByCategoryAndSubcategory(category, subcatagory);
        return products;
    }

    public <T extends Comparable<T>> List<T> mergeSort(List<T> list) {

        if (list == null || list.size() <= 1) {
            return list;
        }

        Collections.shuffle(list);

        int mid = list.size() / 2;

        List<T> left = new ArrayList<>(list.subList(0, mid));
        List<T> right = new ArrayList<>(list.subList(mid, list.size()));

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    public <T extends Comparable<T>> List<T> merge(List<T> left, List<T> right) {
        List<T> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }

        while (i < left.size()) {
            result.add(left.get(i++));
        }

        while (j < right.size()) {
            result.add(right.get(j++));
        }

        return result;
    }

    public List<String> getCategories(){
        List<String> categories = productRepository.findAllCategories();
        return categories;
    }

    public List<Product> getCategoryCards(){
        List<Product> products = productRepository.findDistinctByCategoryAndSubcategory();
        return products;
    }

    public List<Product> getCardsForGame(String category, String subcategory){
        List<Product> products = productRepository.findByCategoryAndSubcategory(category, subcategory);
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