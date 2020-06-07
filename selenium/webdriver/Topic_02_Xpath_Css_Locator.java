package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Locator {
	// Khai báo biến driver (instance = thể hiện / đại diện)
	WebDriver driver;
	// WebDriver là 1 interface includes FirefoxDriver, ChromeDriver, IEDriver,
	// EdgeDriver, SafariDriver, PhantomJSDriver, OperaDriver

	@BeforeClass // Pre-condition
	public void beforeClass() {
		// Khởi tạo trình duyệt Firefox lên
		driver = new FirefoxDriver();
		// Chờ cho element được stable trước khi thao tác trong vòng xx giây
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// phóng to browser lên
		driver.manage().window().maximize();
		// Mở cái app Url
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}
	// Html cua login button:
	// -- <button type="submit" class="button" title="Login" name="send"
	// id="send2"><span><span>Login</span></span></button>

	// Html của Email textbox:
	// -- <input id="email" class="input-text required-entry validate-email"
	// type="email
	// -- title="Email Address" value="" name="login[username]"
	// -- spellcheck="false" autocorrect="off" autocapitalize="off"/>
	// Trong Selenium có 3 loại locator là 3 dạng attribute của HTML (id/ class/
	// name)

	@Test // Test cases: Action/ Verify
	public void TC_01_ID() throws InterruptedException {
		// Tương tác với element
		driver.findElement(By.id("email")).sendKeys("id@gmail.com");
		Thread.sleep(2000);

		// Xoa du lieu trong cac element co the nhap duoc: textbox/ textarea/
		// dropdown/...
		driver.findElement(By.id("email")).clear();
	}

	@Test
	public void TC_02_Class() throws InterruptedException {
		driver.findElement(By.className("input-text required-entry validate-password")).sendKeys("123456");
		Thread.sleep(2000);
		driver.findElement(By.className("input-text required-entry validate-password")).clear();
	}

	@Test
	public void TC_03_Name() throws InterruptedException {
		driver.findElement(By.name("login[username]")).sendKeys("name@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.className("login[username]")).clear();
	}

	@Test
	public void TC_04_Tagname() throws InterruptedException {
		int linkNumber = driver.findElements(By.tagName("a")).size();
		System.out.println("Sum link = " + linkNumber);
		Thread.sleep(2000);
	}

	@Test // No chi work voi duong link voi text tuyet doi
	public void TC_05_Link_Text() throws InterruptedException {
		driver.findElement(By.linkText("SITE MAP")).click();
		Thread.sleep(2000);
	}

	@Test // No chi work voi duong link voi text tuong doi
	public void TC_06_Partial_Link_Text() throws InterruptedException {
		driver.findElement(By.partialLinkText("ADVANCED")).click();
		Thread.sleep(2000);
	}

	@Test
	public void TC_07_Css() throws InterruptedException {
		// ID
		driver.findElement(By.cssSelector("#name")).sendKeys("LCD");

		// Class
		driver.findElement(By.cssSelector(".advanced-search")).isDisplayed();

		// Name
		driver.findElement(By.cssSelector("input[name='short_description']")).sendKeys("Samsung LCD");

		// LinkText
		driver.findElement(
				By.cssSelector("a[href='http://live.demoguru99.com/index.php/catalog/seo_sitemap/category/']")).click();

		// Partial Link
		driver.findElement(By.cssSelector("a[href*='/catalogsearch/advanced/']")).click();

		// Tagname
		int linkSize = driver.findElements(By.cssSelector("a")).size();
		System.out.println("Css Tagname = " + linkSize);
		Thread.sleep(2000);
	}

	@Test
	public void TC_08_Xpath() throws InterruptedException {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");

		// ID
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("abc@gmail.com");
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		Thread.sleep(2000);

		// Class
		driver.findElement(By.xpath("//input[@class='input-text required-entry validate-password']"))
				.sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		Thread.sleep(2000);

		// Name
		driver.findElement(By.xpath("//input[@name='login[password]']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@name='login[password]']")).clear();
		Thread.sleep(2000);

		// LinkText
		driver.findElement(By.xpath("//a[text()='About Us']")).click();
		Thread.sleep(2000);

		// Partial Link
		driver.findElement(By.xpath("//a[contains(text(), 'Customer')]")).click();
		Thread.sleep(2000);

		// Tagname
		System.out.println(driver.findElements(By.xpath("//a")).size());
		Thread.sleep(2000);
		
		//Css
		driver.findElement(By.xpath("//input[@title='Sign up for our newsletter']")).sendKeys("abc@gmail.com");
		Thread.sleep(2000);

	}

	@AfterClass // Post-condition
	public void afterClass() {
		driver.quit();
	}

}
