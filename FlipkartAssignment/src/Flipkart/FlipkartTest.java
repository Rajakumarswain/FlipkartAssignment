package Flipkart;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import com.google.common.io.Files;

public class FlipkartTest {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty("Webdriver.Chrome.driver", "E:/Selenium/FlipkartAssignment/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.flipkart.com");
//		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[1]/input"))
//				.sendKeys("8618369887");
//		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[2]/input"))
//				.sendKeys("test123");
//		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[4]/button")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();

		WebElement Appliance = driver.findElement(By.xpath("//div[text()='Appliances']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(Appliance).build().perform();
		driver.findElement(By.xpath("//a[text()='Televisions']")).click();
		WebElement TvAppliances = driver.findElement(By.xpath("//span[text()='TVs & Appliances']"));
		Actions action1 = new Actions(driver);
		actions.moveToElement(TvAppliances).build().perform();
		WebElement TvAppliancesScrsz = driver.findElement(By.xpath("//a[text()='48 - 55']"));
		action1.moveToElement(TvAppliancesScrsz).click().build().perform();
		Thread.sleep(2000);
		Select lowrange = new Select(driver.findElement(By.xpath("(//select[@class='_2YxCDZ'])[1]")));
		lowrange.selectByValue("20000");
		Thread.sleep(2000);
		Select toprange = new Select(driver.findElement(By.xpath("(//select[@class='_2YxCDZ'])[2]")));
		toprange.selectByValue("60000");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='Resolution']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[@title='Ultra HD (4K)']//input/following-sibling::div[1]")).click();
		Thread.sleep(2000);
		if (driver.findElement(By.xpath("(//div[text()='Exclude Out of Stock'])[1]")).isDisplayed()) {
			driver.findElement(By.xpath("(//div[text()='Exclude Out of Stock'])[1]/preceding-sibling::div")).click();
		}
		driver.findElement(By.xpath("//span[@class='_2tDckM']")).getText();
		System.out.println(driver.findElement(By.xpath("//span[@class='_2tDckM']")).getText());
		Thread.sleep(4000);
		driver.findElement(By
				.xpath("//*[@id='container']/div/div[3]/div/div[2]/div[4]/div/div/div/a/div[1]/div[2]/div/span/label/div"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By
				.xpath("//*[@id='container']/div/div[3]/div/div[2]/div[6]/div/div/div/a/div[1]/div[2]/div/span/label/div"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By
				.xpath("//*[@id='container']/div/div[3]/div/div[2]/div[9]/div/div/div/a/div[1]/div[2]/div/span/label/div"))
				.click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//span[text()='COMPARE']")).click();
		Thread.sleep(2000);
		Takescreenshot("Compared_TvLCD");
		List<WebElement> list_of_products_price = driver.findElements(By.xpath(
				"//div[@class='_30jeq3']/parent::div[@class='_25b18c']/parent::div[@class='col col-1-5 _13lGXD']/div/div[1]"));

		String TV_price;
		HashMap<Integer, Integer> map_final_products = new HashMap<Integer, Integer>();
		for (int i = 0; i < list_of_products_price.size(); i++) {

			TV_price = list_of_products_price.get(i).getText();
			TV_price = TV_price.replaceAll("[^0-9]", "");
			int finalTVprice = Integer.parseInt(TV_price);
			map_final_products.put(i + 1, finalTVprice);

		}
		System.out.println("TV price list" + map_final_products.toString());
		int x = map_final_products.get(1);
		for (int i : map_final_products.values()) {
			if (i < x) {
				x = i;
			}
		}
		System.out.println("Minmum price is =" + x);

		driver.findElement(By.xpath("(//button[@class='_2KpZ6l _2U9uOA _3v1-ww vsi37q'])[1]")).click();

		driver.findElement(By.xpath("(//a[@class='_3hfHKJ'])[1]")).click();
		Thread.sleep(2000);
		String parent = driver.getWindowHandle();
		Set<String> s = driver.getWindowHandles();
		Iterator<String> I1 = s.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);
				driver.findElement(
						By.xpath("/html/body/div/div/div[3]/div/div/div[2]/div[2]/div[1]/div[2]/div[5]/div/div/p"))
						.click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[text()='Yes']")).click();
			}
		}
		// switch to the parent window
		driver.switchTo().window(parent);
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2ObVJD _3AWRsL']")).click();
		System.out.println("Order placed successfully");
		driver.quit();

	}

	private static void Takescreenshot(String filename) throws IOException {
		File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(srcfile, new File("E:/Selenium/FlipkartAssignment/src" + filename + ".png"));
	}

}
