package com.nttdata.stepsdefinitions;

import com.nttdata.steps.InventorySteps;
import com.nttdata.steps.LoginSteps;
import com.nttdata.steps.ShoppingSteps;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginStepsDef {

    private WebDriver driver;
    private Scenario scenario;

    @Before(order = 0)
    public void setUp(){
        //setUp
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        //crear el driver
        driver = new ChromeDriver();
        //max
        driver.manage().window().maximize();
    }

    @Before(order = 1)
    public void setScenario(Scenario scenario){
        this.scenario = scenario;
    }

    @After
    public void quitDriver(){
        driver.quit();
    }

    @Dado("que me encuentro en la página de login de Saucedemo")
    public void que_me_encuentro_en_la_página_de_login_de_sacedemo() {
        driver.get("https://www.saucedemo.com/");
        screenShot();
    }
    @Cuando("inicio sesión con las credenciales usuario: {string} y contraseña: {string}")
    public void inicio_sesión_con_las_credenciales_usuario_y_contraseña(String user, String password) {
        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.typeUser(user);
        loginSteps.typePassword(password);
        loginSteps.login();
        screenShot();
    }
    @Entonces("valido que debería aparecer el título de {string}")
    public void valido_que_debería_aparecer_el_título_de(String expectedTitle) {
        InventorySteps inventorySteps = new InventorySteps(driver);
        String title =  inventorySteps.getTitle();
        //prueba: validamos el título del producto
        Assertions.assertEquals(expectedTitle, title);
    }
    @Y("también valido que al menos exista un item")
    public void también_valido_que_al_menos_exista_un_item() {
        InventorySteps inventorySteps = new InventorySteps(driver);
        int itemsListSize = inventorySteps.getItemSize();
        screenShot();
        //prueba: validar que al menos exista un item
        Assertions.assertTrue(itemsListSize > 0, "El tamaño de la lista es: " + itemsListSize);
    }
    @Y("agrego un item al carrito de compras")
    public void agrego_un_item_al_carrito_de_compras() {
        InventorySteps inventorySteps = new InventorySteps(driver);
        //Se agrega un item
        inventorySteps.shopping();
        screenShot();
        //Se redirecciona a la pagina del carrito de compras
        inventorySteps.shoppingcartLink();
    }
    @Y("reviso que el carrito de compras tenga un item agregado")
    public void reviso_que_el_carrito_de_compras_tenga_un_item_agregado() {
        ShoppingSteps shoppingSteps = new ShoppingSteps(driver);
        int itemsListSize = shoppingSteps.getItemSize();
        screenShot();
        //prueba: validar que el carrito de compras tenga un item agregado
        Assertions.assertTrue(itemsListSize == 1, "El tamaño de la lista es: " + itemsListSize);
    }
    @Y("valido que el item agregado se llame {string}")
    public void valido_que_el_item_agregado_se_llame(String expectedName) {
        ShoppingSteps shoppingSteps = new ShoppingSteps(driver);
        String name =  shoppingSteps.getName();
        //prueba: validamos el título del producto
        Assertions.assertEquals(expectedName, name);
    }
    public void screenShot(){
        byte[] evidencia = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        this.scenario.attach(evidencia, "image/png", "evidencias");
    }
}
