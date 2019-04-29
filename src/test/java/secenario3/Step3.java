package secenario3;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Step3 {
	
	WebDriver driver;
	@Given("larry creates a webdriver instance")
	public void larry_creates_a_webdriver_instance() {
		System.setProperty("webdriver.chrome.driver","C:\\Driver\\chromedriver.exe" );
		driver = new ChromeDriver();
	}

	@And("she opens Test me app URL")
	public void she_opens_Test_me_app_URL() {
		driver.get("http://10.232.237.143:443/TestMeApp");
	}

	@And("Login to application by giving admin username and password and clicks on login button")
	public void login_to_application_by_giving_admin_username_and_password_and_clicks_on_login_button(io.cucumber.datatable.DataTable dt) {
		List<Map<String,String>> list = dt.asMaps(String.class,String.class);
		
		
		String username = list.get(0).get("username");
		String password = list.get(0).get("password");
	
	driver.findElement(By.xpath("//a[@href='login.htm']")).click();
	driver.findElement(By.xpath("//input[@name='userName']")).sendKeys(username);
	driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
	driver.findElement(By.xpath("//input[@type='submit']")).click();
	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	String page = driver.getTitle();
	Assert.assertEquals(page, "Admin Home");
	}

	@Then("click on Add product selects category as {string} and subcategory as {string}")
	public void click_on_Add_product_selects_category_as_and_subcategory_as(String categoryname, String Subcategoryname) {
		driver.findElement(By.xpath("//h4[text()='Add Product']")).click();
		Select category = new Select(driver.findElement(By.xpath("//select[@id='categorydropid']")));
		category.selectByVisibleText(categoryname);
		
		Select subcategory = new Select(driver.findElement(By.xpath("//select[@id='subcategorydropid']")));
		subcategory.selectByVisibleText(Subcategoryname);
	}

	@And("give productname {string} price {} quantity {} brand {string} Description {string}")
	public void give_productname_price_quantity_brand_Description(String PN, String price, String quant, String Brand, String des) {
		driver.findElement(By.xpath("//input[@name='prodname']")).sendKeys(PN);
		driver.findElement(By.xpath("//input[@name='pricename']")).sendKeys(price);
		driver.findElement(By.xpath("//input[@name='Quantity']")).sendKeys(quant);
		
		driver.findElement(By.xpath("//input[@name='Brand']")).sendKeys(Brand);
		driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys(des);
		WebElement pic = driver.findElement(By.xpath("//input[@type='file']"));
			pic.sendKeys("C:\\Users\\chaithra.a.manjunath\\Desktop\\Boat.jpg");
		driver.findElement(By.xpath("//input[@name='Add Product']")).click();
	}

	@And("comebacks to home page and logout from admin page")
	public void comebacks_to_home_page_and_logout_from_admin_page() {
		driver.findElement(By.xpath("//a[@href='adminHome.htm']")).click();
		driver.findElement(By.xpath("//a[@href='logout.htm']")).click();
	}

	@Then("it navigated to Application home page")
	public void it_navigated_to_Application_home_page() {
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		String page = driver.getTitle();
		Assert.assertEquals(page, "Sign Out");
		driver.close();
	}

}
