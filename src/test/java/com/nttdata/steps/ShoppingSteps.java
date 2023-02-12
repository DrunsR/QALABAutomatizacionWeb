package com.nttdata.steps;

import com.nttdata.page.ShoppingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class ShoppingSteps {
    private WebDriver driver;
    public ShoppingSteps(WebDriver driver){
        this.driver = driver;
    }
    public int getItemSize(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(444));
        List<WebElement> items = this.driver.findElements(ShoppingPage.cartItems);
        return items.size();
    }
    public String getName(){
        return this.driver.findElement(ShoppingPage.itemName).getText();
    }
}
