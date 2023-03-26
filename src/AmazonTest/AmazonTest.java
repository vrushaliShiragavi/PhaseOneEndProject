package AmazonTest;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonTest {
	public static void main(String[] args) throws IOException {

		// Launch chrome browser and open : https://www.amazon.in/
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

		// Search for : samsung mobile
		WebElement searchTextBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchTextBox.sendKeys("samsung mobile");

		// Click on the search button
		WebElement searchBtn = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
		searchBtn.click();

		List<WebElement> product_names = driver.findElements(By.xpath("//div[@class='a-section']//h2//span"));
		System.out.println("Total number of product names : " + product_names.size());

		List<WebElement> product_price = driver.findElements(By.xpath(
				"//div[@data-component-type='s-search-result']//div[contains(@class,'price')]//span[@class='a-price-whole']"));
		System.out.println("Total number of product price : " + product_price.size());

		List<WebElement> price_symbol = driver.findElements(By.xpath(
				"//div[@data-component-type='s-search-result']//div[contains(@class,'price')]//span[@class='a-price-symbol']"));
		System.out.println("Total number of price symbol : " + price_symbol.size());

		// Print the results on the console
		for (int i = 0; i < product_names.size(); i++) {
			System.out.println("Product : " + product_names.get(i).getText());
			System.out.println("Price : " + price_symbol.get(i).getText() + " " + product_price.get(i).getText());
		}

		// ) Take the screenshot of page
		TakesScreenshot Tsobj = (TakesScreenshot) driver;
		File fileObj = Tsobj.getScreenshotAs(OutputType.FILE);
		File scrnShotObj = new File("image.png");
		FileUtils.copyFile(fileObj, scrnShotObj);

		// Close Browser
		driver.quit();
	}
}
