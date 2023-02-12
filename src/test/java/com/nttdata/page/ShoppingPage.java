package com.nttdata.page;

import org.openqa.selenium.By;

public class ShoppingPage {
    //Localizadores de elementos

    //items del carrito de compras
    public static By cartItems = By.cssSelector("div.cart_item");

    //Nombre del item
    public static By itemName = By.cssSelector("div.inventory_item_name");
}
