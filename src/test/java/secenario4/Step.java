package secenario4;

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

public class Step {
	WebDriver driver;
	String subcategory=null;
	String category = null;
	@Given("larry  create a webdriver instance")
	public void larry_create_a_webdriver_instance() {
		System.setProperty("webdriver.chrome.driver","C:\\Driver\\chromedriver.exe" );
		driver = new ChromeDriver();
	}

	@And("she opened Test me app URL")
	public void she_opened_Test_me_app_URL() {
		driver.get("http://10.232.237.143:443/TestMeApp");
	}

	@And("She enter valid credentials and logged into admin page")
	public void she_enter_valid_credentials_and_logged_into_admin_page(io.cucumber.datatable.DataTable dt) {
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

	@Then("she added category as {string} and subcategory as {string}")
	public void she_added_category_as_and_subcategory_as(String categoryname, String Subcategoryname) {
		driver.findElement(By.xpath("//h4[text()='Add Product']")).click();
		 category=categoryname;
		//Select category = new Select(driver.findElement(By.xpath("//select[@id='categorydropid']")));
	//	category.selectByVisibleText(categoryname);
		subcategory=Subcategoryname;
		///Select subcategory = new Select(driver.findElement(By.xpath("//select[@id='subcategorydropid']")));
		//subcategory.selectByVisibleText(Subcategoryname);
	}

	@And("give productname  price {} quantity {} brand {string} Description {string}")
	public void give_productname_price_quantity_brand_Description(String price, String quant, String Brand, String des, io.cucumber.datatable.DataTable dt) throws InterruptedException {

		List<Map<String,String>> list = dt.asMaps(String.class,String.class);
		for(int j=0;j<list.size();j++)
		
		{
			Select categoryN = new Select(driver.findElement(By.xpath("//select[@id='categorydropid']")));
				categoryN.selectByVisibleText(category);
			
			Select subcategoryN = new Select(driver.findElement(By.xpath("//select[@id='subcategorydropid']")));
			subcategoryN.selectByVisibleText(subcategory);
			
			String PNa = list.get(j).get("PN");
			driver.findElement(By.xpath("//input[@name='prodname']")).sendKeys(PNa);
			driver.findElement(By.xpath("//input[@name='pricename']")).sendKeys(price);
			driver.findElement(By.xpath("//input[@name='Quantity']")).sendKeys(quant);
			
			driver.findElement(By.xpath("//input[@name='Brand']")).sendKeys(Brand);
			driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys(des);
			System.out.println(PNa);
			if(j == 0)
			{
			WebElement pic = driver.findElement(By.xpath("//input[@type='file']"));
				pic.sendKeys("c:\\Users\\chaithra.a.manjunath\\Desktop\\Ball.jpg");	
			}
			if(j ==1)
			{
			WebElement pic = driver.findElement(By.xpath("//input[@type='file']"));
				pic.sendKeys("C:\\Users\\chaithra.a.manjunath\\Desktop\\Bat.png");
			}
			driver.findElement(By.xpath("//input[@name='Add Product']")).click();
			Thread.sleep(20000);
	}
	}

	@And("she logout from admin page")
	public void she_logout_from_admin_page() {
	   driver.close();
	}

	@Then("page navigated to Application home page")
	public void page_navigated_to_Application_home_page() {
	
	}

}
