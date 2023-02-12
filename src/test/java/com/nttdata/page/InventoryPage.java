package com.nttdata.page;

import org.openqa.selenium.By;

public class InventoryPage {
    //Localizadores de elementos
    //Titulo de la página
    public static By productsTitle = By.cssSelector("span.title");
    //items del iventario
    public static By itemsCards = By.cssSelector("div.inventory_item");
    //Boton de agregar a carrito el item Sauce Labs Backpack
    public static By cartButton = By.name("add-to-cart-sauce-labs-bike-light");
    //Boton de redireccion a la pagina del carrito de compras
    public static By shoppingcartButton = By.id("shopping_cart_container");
}
