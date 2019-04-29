package senario2;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Steps {
	WebDriver driver;

@Given("Larry creates a webdriver instance")
public void larry_creates_a_webdriver_instance() {
	System.setProperty("webdriver.chrome.driver","C:\\Driver\\chromedriver.exe" );
	driver = new ChromeDriver();
}

@And("opens Testme app URL")
public void opens_Testme_app_URL() {
	driver.get("http://10.232.237.143:443/TestMeApp");
}

@And("Login to app by giving username and password and clicks on login button")
public void login_to_app_by_giving_username_and_password_and_clicks_on_login_button(io.cucumber.datatable.DataTable dt) {
	List<Map<String,String>> list = dt.asMaps(String.class,String.class);
	
	
	String username = list.get(0).get("username");
	String password = list.get(0).get("password");

driver.findElement(By.xpath("//a[@href='login.htm']")).click();
driver.findElement(By.xpath("//input[@name='userName']")).sendKeys(username);
driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
driver.findElement(By.xpath("//input[@type='submit']")).click();
driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
String page = driver.getTitle();
Assert.assertEquals(page, "Home");
}

@And("searches a product in search bar and checks is the product is same as searched product")
public void searches_a_product_in_search_bar_and_checks_is_the_product_is_same_as_searched_product() {
    driver.findElement(By.xpath("//input[@name='products']")).sendKeys("Shoes");
	Actions actions = new Actions(driver);
	actions.sendKeys(Keys.ENTER).perform();
	String page = driver.getTitle();
	Assert.assertEquals(page, "Search");
	String text = driver.findElement(By.xpath("//h4[text()='Shoes']")).getText();
	Assert.assertTrue(text.contains("Shoes"));

}

@Then("she added that product to cart and checked out for payment")
public void she_added_that_product_to_cart_and_checked_out_for_payment() {
	driver.findElement(By.xpath("//a[@class='btn btn-success btn-product']")).click();
	String text1 = driver.findElement(By.xpath("//h3[text()='Sorry no products available in this category. Please try some other']")).getText();
	Assert.assertTrue(text1.contains("Sorry"));
	driver.findElement(By.xpath("//a[@href='displayCart.htm']")).click();
	String page1 = driver.getTitle();
	Assert.assertEquals(page1, "View Cart");
	driver.findElement(By.xpath("//a[@href='checkout.htm']")).click();
	String page2 = driver.getTitle();
	Assert.assertEquals(page2, "Cart Checkout");
	driver.findElement(By.xpath("//input[@value='Proceed to Pay']")).click();
}

@Then("she redirected to payment gate way and she selected Andhrabank")
public void she_redirected_to_payment_gate_way_and_she_selected_Andhrabank() {
	driver.findElement(By.xpath("//*[@id='swit']/div[1]/div/label/i")).click();
	driver.findElement(By.xpath("//a[@href='#']")).click();
	String value = driver.findElement(By.xpath("//*[@id='horizontalTab']/div[2]/div/div/div/div/h2")).getText();
	Assert.assertTrue(value.contains("Login"));
}

@And("she enters the net banking userid password and transaction id and ordered the product")
public void she_enters_the_net_banking_userid_password_and_transaction_id_and_ordered_the_product(io.cucumber.datatable.DataTable dt) {
List<Map<String,String>> list = dt.asMaps(String.class,String.class);
	
	
	String username = list.get(0).get("userid");
	String password = list.get(0).get("password");
	driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
	driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
	driver.findElement(By.xpath("//input[@value='LOGIN']")).click();
	String pageinfo = driver.findElement(By.xpath("//*[@id='horizontalTab']/div[2]/div/div/h4")).getText();
	Assert.assertTrue(pageinfo.contains("Summary"));
	driver.findElement(By.xpath("//input[@name='transpwd']")).sendKeys("Trans@456");
	driver.findElement(By.xpath("//input[@value='PayNow']")).click();
	String finale = driver.findElement(By.xpath("/html/body/b/section/div/div/div/div[2]/p")).getText();
	Assert.assertTrue(finale.contains("confirmed"));
	
}

@Then("she logout out and closed app")
public void she_logout_out_and_closed_app() {
    driver.close();
}

	
	

}
