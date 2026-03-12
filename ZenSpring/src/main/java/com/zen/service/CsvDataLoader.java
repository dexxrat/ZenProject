package com.zen.service;


import com.zen.model.Product;
import com.zen.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Component
public class CsvDataLoader {

    private final ProductRepository productRepository;
    List<Product> products = new ArrayList<>();


    public CsvDataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void init() throws IOException {
        if (productRepository.count()>0){
            System.out.println("Данных есть");
            return ;
        }else {
            InputStream is = getClass().getResourceAsStream("/zara_products.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            String header = reader.readLine();
            while ((line = reader.readLine())!=null){
                String[] data = line.split(";");
                String priceStr = data[1]
                        .replace("\"", "")     // убираем кавычки
                        .replace("PLN", "")    // убираем PLN
                        .replace(",","")
                        .trim();

                Product product = new Product();
                product.setName(data[0].replace("\"", "").trim());
                product.setPrice(new BigDecimal(priceStr));
                product.setLink(data[2].replace("\"", "").trim());
                product.setImgUrl(data[3].replace("\"", "").trim());
                product.setColor(data[4].replace("\"", "").trim());
                product.setCategory(data[5].replace("\"", "").trim());
                product.setSubcategory(data[6].replace("\"", "").trim());
                products.add(product);
            }

            productRepository.saveAll(products);

        }


    }



}
