package secenario1;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Step_def {
	
	WebDriver driver;
	String categoryname = null;
	String Subcategoryname= null;
	String PN = "Shoes";
	String BN = "Nike";
	
	@Given("Alex creates a webdriver instance")
	public void alex_creates_a_webdriver_instance() {
		System.setProperty("webdriver.chrome.driver","C:\\Driver\\chromedriver.exe" );
		driver = new ChromeDriver();
	}

	@And("opens Test me app URL")
	public void opens_Test_me_app_URL() {
		driver.get("http://10.232.237.143:443/TestMeApp");
	}

	@And("Login to app by giving admin username and password and clicks on login button")
	public void login_to_app_by_giving_admin_username_and_password_and_clicks_on_login_button(io.cucumber.datatable.DataTable dt) {
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

	@And("Click on Add Categories and fills mandatory details and add the category")
	public void click_on_Add_Categories_and_fills_mandatory_details_and_add_the_category(io.cucumber.datatable.DataTable dt) {
			List<Map<String,String>> list = dt.asMaps(String.class,String.class);
		
		
		 categoryname = list.get(0).get("categoryname");
		String Description = list.get(0).get("Description");
		
		driver.findElement(By.xpath("//h4[text()='Add Category']")).click();
		driver.findElement(By.xpath("//input[@name='catgName']")).sendKeys(categoryname);
		
		driver.findElement(By.xpath("//input[@name='catgDesc']")).sendKeys(Description);
		driver.findElement(By.xpath("//input[@value='Add']")).click();
		driver.findElement(By.xpath("//a[@href='adminHome.htm']")).click();
		
		
	}

	@And("Click on Add SubCategories and fills mandatory details and add the Subcategory")
	public void click_on_Add_SubCategories_and_fills_mandatory_details_and_add_the_Subcategory(io.cucumber.datatable.DataTable dt) {
	List<Map<String,String>> list = dt.asMaps(String.class,String.class);
		
		
		Subcategoryname = list.get(0).get("Subcategoryname");
		String Description = list.get(0).get("Description");
		
		driver.findElement(By.xpath("//h4[text()='Add Sub Category']")).click();
		Select question = new Select(driver.findElement(By.xpath("//select[@id='subCatgId']")));
		question.selectByVisibleText(categoryname);
		driver.findElement(By.xpath("//input[@name='subCatgName']")).sendKeys(Subcategoryname);
		
		driver.findElement(By.xpath("//input[@name='subCatgDesc']")).sendKeys(Description);
		driver.findElement(By.xpath("//input[@value='Add']")).click();
		driver.findElement(By.xpath("//a[@href='adminHome.htm']")).click();
		
		
	}

	@Then("click on Add product and fills mandatory details and add the product")
	public void click_on_Add_product_and_fills_mandatory_details_and_add_the_product() {
		driver.findElement(By.xpath("//h4[text()='Add Product']")).click();
		Select category = new Select(driver.findElement(By.xpath("//select[@id='categorydropid']")));
		category.selectByVisibleText(categoryname);
		
		Select subcategory = new Select(driver.findElement(By.xpath("//select[@id='subcategorydropid']")));
		subcategory.selectByVisibleText(Subcategoryname);
		
		driver.findElement(By.xpath("//input[@name='prodname']")).sendKeys(PN);
		driver.findElement(By.xpath("//input[@name='pricename']")).sendKeys("2000");
		driver.findElement(By.xpath("//input[@name='Quantity']")).sendKeys("20");
		
		driver.findElement(By.xpath("//input[@name='Brand']")).sendKeys(BN);
		driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("Very good");
		WebElement pic = driver.findElement(By.xpath("//input[@type='file']"));
			pic.sendKeys("C:\\Users\\chaithra.a.manjunath\\Desktop\\Nike.jpeg");
		driver.findElement(By.xpath("//input[@name='Add Product']")).click();
		
	}

	@And("comeback to home page and logout from admin page")
	public void comeback_to_home_page_and_logout_from_admin_page() {
		driver.findElement(By.xpath("//a[@href='adminHome.htm']")).click();
		driver.findElement(By.xpath("//a[@href='logout.htm']")).click();
	}

	@Then("it navigated to App home page")
	public void it_navigated_to_App_home_page() {
	/*	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		String page = driver.getTitle();
		Assert.assertEquals(page, "Home");*/
	}


	
	
	@And("he checks product is present or not")
	public void he_checks_product_is_present_or_not() {
		driver.findElement(By.xpath("//input[@name='products']")).sendKeys(PN);
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ENTER).perform();
		String page = driver.getTitle();
		Assert.assertEquals(page, "Search");
		String text = driver.findElement(By.xpath("//span[@class='label label-info']")).getText();
		Assert.assertEquals(text, BN);
		driver.close();
	}



}
