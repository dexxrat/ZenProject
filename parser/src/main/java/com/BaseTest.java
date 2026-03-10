package com;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.*;

public class BaseTest {

    List<Product> allProducts = new ArrayList<>();
    Set<String> uniqueLinks = new HashSet<>();

    private static WebDriver driver = new ChromeDriver();

    private String siteUrl = "https://www.zara.com/pl/en/";
    private By closeKrestik = By.xpath("//*[@id=\"onetrust-close-btn-container\"]/button");
    private By agreeWithPolans = By.xpath("//*[@id=\"I2024-HOME\"]/body/div[2]/div[2]/div/div/div[2]/section[1]/button[1]/span");
    private By menuButton = By.xpath("/html/body/div[1]/div/div[1]/div/div/div/div[2]/header/div[1]/button");
    private By buttonMan =  By.xpath("//div[@class='zds-carousel-container']/div[2]/a[2]");
    private By buttonAll = By.xpath("(//li/a[@data-qa-action='unfold-category' and contains(span, 'T-SHIRTS')])[2]");
    private By buttonLeater = By.xpath("//*[@id=\"theme-app\"]/div/div/div/div[2]/main/div[1]/nav/ul/li[6]/a");

    private By product1IMG = By.xpath(".//div[1]/a/div/div/div/img");
    private By productName = By.xpath(".//div[2]/div/div/div/div[1]/a/h3");
    private By productCost = By.xpath(".//div[2]/div/div/div/div[3]/span/span/span/div/span");
    private By productLink = By.xpath(".//div[2]/div/div/div/div[1]/a");

    private By products = By.xpath("//li[contains(@class, 'product-grid-product')]");


    private String currentCategory = "";
    private String currentSubcategory = "";

    public void setUp()  {
        System.setProperty("webdriver.chrome.driver", "C:\\Projects\\ZaraSelenium\\src\\main\\resources\\chromedriver.exe");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get(siteUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public BaseTest agreeWithSite(){
        try {
            driver.findElement(closeKrestik).click();
            System.out.println("Крестик закрыт");
            Thread.sleep(1500);
        } catch (Exception e) {
            System.out.println("Нет крестика");
        }
        try {
            driver.findElement(agreeWithPolans).click();
            System.out.println("Согласие принято");
            Thread.sleep(1500);
        } catch (Exception e) {
            System.out.println("Нет окна согласия");
        }
        return this;
    }

    public BaseTest goToManMenu() throws InterruptedException {
        driver.findElement(menuButton).click();
        Thread.sleep(1000);
        driver.findElement(buttonMan).click();
        Thread.sleep(1000);


        try {
            WebElement categoryElement = driver.findElement(buttonAll);
            currentCategory = categoryElement.getText().trim();
            System.out.println("Категория: " + currentCategory);
        } catch (Exception e) {
            System.out.println("Не удалось получить категорию");
        }

        driver.findElement(buttonAll).click();
        Thread.sleep(1000);


        try {

            List<WebElement> navLinks = driver.findElements(By.xpath("//*[@id=\"theme-app\"]/div/div/div/div[2]/main/div[1]/nav/ul/li/a"));

            System.out.println(" Найденные подкатегории в навигации:");
            for (int i = 0; i < navLinks.size(); i++) {
                WebElement link = navLinks.get(i);
                String linkText = link.getText().trim();
                String cleanText = linkText.replaceAll("^[0-9]+", "").trim();
                System.out.println("  [" + (i+1) + "] " + linkText + " -> очищено: '" + cleanText + "'");


            }




            try {
                WebElement leatherButton = driver.findElement(buttonLeater);
                String leatherText = leatherButton.getText().trim();
                currentSubcategory = leatherText.replaceAll("^[0-9]+", "").trim();
                System.out.println("Подкатегория из кнопки LEATHER: " + currentSubcategory);
            } catch (Exception e) {
                System.out.println(" Не удалось получить текст из buttonLeater");
            }




            if (currentSubcategory.isEmpty() && navLinks.size() >= 2) {
                String secondText = navLinks.get(1).getText().trim();
                currentSubcategory = secondText.replaceAll("^[0-9]+", "").trim();
                System.out.println(" Подкатегория (второй элемент): " + currentSubcategory);
            }

        } catch (Exception e) {
            System.out.println(" Ошибка при получении подкатегорий: " + e.getMessage());
        }

        driver.findElement(buttonLeater).click();
        Thread.sleep(2000);

        return this;
    }

    public BaseTest recieveProducts() throws InterruptedException {
        List<WebElement> productList;
        Actions actions = new Actions(driver);

        int iteration = 0;
        int expectedTotal = 47;

        while (iteration < 2 && allProducts.size() < expectedTotal) {
            iteration++;

            if (iteration > 1) {
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
                Thread.sleep(1500);
            }

            productList = driver.findElements(products);
            System.out.println("Итерация " + iteration + ": найдено " + productList.size() + " товаров ---");

            for (WebElement product : productList) {
                try {
                    WebElement linkElement = product.findElement(productLink);

                    actions.contextClick(linkElement).perform();
                    Thread.sleep(5);
                    actions.sendKeys(Keys.ESCAPE).perform();
                    Thread.sleep(5);

                    String link = linkElement.getAttribute("href");
                    if (link == null) continue;

                    String color = null;
                    try {
                        color = product.findElement(By.xpath(".//div[2]/div/div/div/p/span[1]"))
                                .getAttribute("aria-label");
                    } catch (Exception e) {}

                    String uniqueKey = link + "_" + (color != null ? color : "NO_COLOR");

                    if (uniqueLinks.add(uniqueKey)) {
                        String img = product.findElement(product1IMG).getAttribute("src");
                        String name = product.findElement(productName).getText();
                        String cost = product.findElement(productCost).getText();


                        allProducts.add(new Product(name, link, cost, img, color, currentCategory, currentSubcategory));
                        System.out.println("  ✓ " + name +
                                (color != null ? " (" + color + ")" : "") +
                                (!currentCategory.isEmpty() ? " [" + currentCategory + "]" : "") +
                                (!currentSubcategory.isEmpty() ? " / " + currentSubcategory : ""));
                    }
                } catch (Exception e) {

                }
            }

            System.out.println("Товаров на странице: " + productList.size() + ", собрано: " + allProducts.size());
        }

        System.out.println(" Итог : " + allProducts.size() + " товаров");
        return this;
    }

    public BaseTest saveProducts(){
        Product.saveToCsv(allProducts, "zara_products.csv");
        System.out.println(" allProducts: " + allProducts.size());
        return this;
    }

    public static void main(String[] args) throws InterruptedException {
        BaseTest test = new BaseTest();
        test.setUp();
        test.agreeWithSite()
                .goToManMenu()
                .recieveProducts()
                .saveProducts();
        driver.quit();
    }
}