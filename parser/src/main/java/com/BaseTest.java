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
    private By agreeWithPolans = By.xpath("//*[@id=\"I2024-HOME\"]/body/div[3]/div[2]/div/div/div[2]/section[1]/button[1]/span");
    private By menuButton = By.xpath("/html/body/div[1]/div/div[1]/div/div/div/div[2]/header/div[1]/button");
    private By buttonMan =  By.xpath("//div[@class='zds-carousel-container']/div[2]/a[2]");


    private By buttonAll = By.xpath("(//li/a[@data-qa-action='unfold-category' and contains(span, 'VIEW ALL')])");
    private By buttonLeater = By.xpath("//*[@id=\"theme-app\"]/div/div/div/div[2]/main/div[1]/nav/ul/li[1]/a");

    private By allCategories = By.xpath("/html/body/div[1]/div/div[1]/div/div/div/div[1]/div/div/div/div/div[2]/div/nav/div/div[2]/div/div/div[2]/ul[1]/li[5]/div/ul/li/a/span");
    private By subCategories = By.xpath("//*[@id=\"main\"]/div[1]/nav/ul/li/a");

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

    }

    public BaseTest allCategories() throws InterruptedException {
        List<WebElement> allCategory = driver.findElements(allCategories);
        Thread.sleep(3000);
        for (int i = 4; i <allCategory.size()-4; i++) {
            allCategory = driver.findElements(allCategories);

            String categoryName = allCategory.get(i).getText();
            currentCategory = categoryName;

            try {
                allCategory.get(i).click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                        allCategory.get(i)
                );
                Thread.sleep(1000);
                try {
                    allCategory.get(i).click();
                } catch (Exception e2) {

                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",  allCategory.get(i));
                }
            }
            allSubCategories();
        }
        currentCategory = "";
        return this;
    }

    public BaseTest allSubCategories() throws InterruptedException {


        Thread.sleep(3000);
        List<WebElement> subCategory = driver.findElements(subCategories);
        Thread.sleep(3000);
        for (int i = 1; i <subCategory.size(); i++) {
            subCategory = driver.findElements(subCategories);

            String subName = subCategory.get(i).getText();
            currentSubcategory = subName.replaceAll("[0-9\\n]", "").trim();

            Thread.sleep(2000);
            subCategory.get(i).click();
            Thread.sleep(2000);
            recieveProducts();
        }
        driver.findElement(menuButton).click();
        Thread.sleep(1000);

        currentSubcategory = "";

        return this;
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


//        try {
//            WebElement categoryElement = driver.findElement(buttonAll);
//            currentCategory = categoryElement.getText().trim();
//            System.out.println("Категория: " + currentCategory);
//        } catch (Exception e) {
//            System.out.println("Не удалось получить категорию");
//        }

        return this;
    }

    public BaseTest recieveProducts() throws InterruptedException {
        Actions actions = new Actions(driver);

        int expectedTotal = 5000;
        int lastSize = 0;
        int sameSizeCount = 0;
        int processedCount = 0;


        while (allProducts.size() < expectedTotal && sameSizeCount < 3) {

            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(10);

            List<WebElement> productList = driver.findElements(products);
            int currentSize = productList.size();

            if (currentSize == lastSize) {
                sameSizeCount++;
            } else {
                sameSizeCount = 0;
            }
            lastSize = currentSize;

            System.out.println("На странице " + currentSize + " товаров, обработано: " + processedCount);

            int newlyAdded = 0;

            for (int i = processedCount; i < productList.size(); i++) {
                try {
                    WebElement product = productList.get(i);
                    WebElement linkElement = product.findElement(productLink);


                    actions.contextClick(linkElement).perform();
                    Thread.sleep(5);
                    actions.sendKeys(Keys.ESCAPE).perform();
                    Thread.sleep(5);

                    String link = linkElement.getAttribute("href");
                    if (link == null || link.isEmpty()) continue;

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
                        newlyAdded++;
                    }
                } catch (Exception e) {

                }
            }

            processedCount = productList.size();

            System.out.println(" новые " + newlyAdded + ", всего: " + allProducts.size() + "/" + expectedTotal);


            if (allProducts.size() >= expectedTotal) {
                System.out.println("Достигнуто целевое количество товаров!");
                break;
            }
        }

        System.out.println("собрано " + allProducts.size() + " товаров");
        return this;
    }

    public BaseTest saveProducts(){
        Product.saveToCsv(allProducts, "zara_products.csv");
        System.out.println(" allProducts: " + allProducts.size());
        return this;
    }

    public static void main(String[] args) throws InterruptedException {
        BaseTest test = new BaseTest();
        try {
            test.setUp();
            test.agreeWithSite()
                    .goToManMenu()
                    .allCategories();
        } catch (Exception e) {
            System.out.println(" Произошла ошибка: " + e.getMessage());
            e.printStackTrace();
        } finally {

            System.out.println("Финальное сохранение...");
            test.saveProducts();
            System.out.println(" Всего сохранено товаров: " + test.allProducts.size());


            if (driver != null) {
//                driver.quit();
                System.out.println("Браузер закрыт");
            }
        }
    }

//        test.agreeWithSite()
//                .goToManMenu()
//                .recieveProducts()
//                .saveProducts();

    }
