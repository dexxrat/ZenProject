package com;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class Product {

    private String name;
    private String link;
    private String price;
    private String imageUrl;
    private String color;
    private String category;
    private String subcategory;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getSubcategory() { return subcategory; }
    public void setSubcategory(String subcategory) { this.subcategory = subcategory; }

    public Product(String name, String link, String price, String imageUrl, String color, String category, String subcategory) {
        this.name = name;
        this.link = link;
        this.price = price;
        this.imageUrl = imageUrl;
        this.color = color;
        this.category = category;
        this.subcategory = subcategory;
    }

    public static void saveToCsv(List<Product> products, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {

            writer.println("Name;Price;Link;Img;Color;Category;Subcategory");

            for (Product p : products) {
                String name = p.getName() != null ? p.getName().replace("\"", "\"\"") : "";
                String price = p.getPrice() != null ? p.getPrice().replace("\"", "\"\"") : "";
                String link = p.getLink() != null ? p.getLink().replace("\"", "\"\"") : "";
                String img = p.getImageUrl() != null ? p.getImageUrl().replace("\"", "\"\"") : "";
                String color = p.getColor() != null ? p.getColor().replace("\"", "\"\"") : "NO_COLOR";
                String category = p.getCategory() != null ? p.getCategory().replace("\"", "\"\"") : "NO_CATEGORY";
                String subcategory = p.getSubcategory() != null ? p.getSubcategory().replace("\"", "\"\"") : "NO_SUBCATEGORY";

                writer.println(String.format("\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\"",
                        name, price, link, img, color, category, subcategory));
            }
            System.out.println("SAVE " + filename + " : " + products.size() + ")");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", color='" + color + '\'' +
                ", category='" + category + '\'' +
                ", subcategory='" + subcategory + '\'' +
                '}';
    }
}