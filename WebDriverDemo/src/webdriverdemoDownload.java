
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class webdriverdemoDownload {
	static String gmailURL = "https://mail.google.com/mail/u/0/h/1pq68r75kzvdr/?v%3Dlui";

	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "seleniumDrivers/geckodriver.exe");
		System.setProperty("webdriver.phantomjs.driver", "seleniumDrivers/phantomjs.exe");

	
		if(SystemUtils.IS_OS_WINDOWS) {
		System.setProperty("webdriver.chrome.driver", "seleniumDrivers/chromedriver.exe");}
		else if(SystemUtils.IS_OS_LINUX) {
		System.setProperty("webdriver.chrome.driver", "seleniumDrivers/chromedriver");}
		else {
			//TODO: send error
		}
		
	
		ChromeOptions options = new ChromeOptions();
//		options.addArguments("headless");
//      options.addArguments("window-size=1200x600");
//      options.addArguments("window-size=1000x800");
		options.addArguments("window-size=1024x2000");
		
		File file = new File("input/Default");
		String profilePath=file.getAbsolutePath();
		options.addArguments("user-data-dir="+profilePath);
		
//		chrome://settings/content/pdfDocuments
//		//*[@id="labelWrapper"]/div[1]
//		click

		WebDriver driver = new ChromeDriver(options);

		try {
			// Puts an Implicit wait, Will wait for 10 seconds before throwing exception
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Launch website
			driver.navigate().to("https://myline-eon.ro/login");

			String s = "@gmail.com", ss = "";

			login(driver, s, ss);
			
			String ld=
			"(//*[@id=\"invoices\"]//div[@class=\"eon-table-content\"])[1]//span[@class=\"eon-icon download\"]";
			
			driver.findElement(By.xpath(ld)).click();
			
//			 ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
//			 driver.switchTo().window(tabs2.get(1));
			 

//				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		//Now you can do whatever you need to do with it, for example copy somewhere
//				try {
//					FileUtils.copyFile(scrFile, new File("output/screenshot" + 9 + ".png"));
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			 
			 
			//TODO: (//*[@id="invoices"]//div[@class="eon-table-content"])[1]//span[@class="eon-icon download"]
			//	(//*[@id="invoices"]//div[@class="eon-table-content"])[1]//span[@class="eon-icon download"]
			//		doxnload invoice
			
			
			// Print a Log In message to the screen
			myPrint(" The Result is " + "haha");
		} finally {
			// Close the Browser.
			driver.close();
		}
	}

	private static void myPrint(String s) {
//		System.out.println(s);
	}

	@SuppressWarnings("unused")
	private static List<String> getAllHOmesOptions(WebDriver driver) {
		List<String> collect = getOtherHomesOptions(driver);

		String sss = "//*[@id=\"select-trigger\"]";
		String locConsum = driver.findElement(By.xpath(sss)).getText();

		collect.add(0, locConsum);

		return collect;
	}

	private static List<String> getOtherHomesOptions(WebDriver driver) {
		List<String> collect;
		WebElement triggerListOpen = driver.findElement(By.xpath("//*[@id=\"select-trigger\"]"));
		triggerListOpen.click();
		List<WebElement> findElements = driver.findElements(By.xpath("//ul[@id='select-list']/li/a/span[2]"));
		collect = findElements.parallelStream().map(e -> e.getText()).collect(Collectors.toList());
		triggerListOpen.click();
		return collect;
	}

	private static int printNumberTable(WebDriver driver) {
		int numerOfLInesTable = driver.findElements(By.xpath("//ul[@id='select-list']/li")).size();

		return numerOfLInesTable;

	}

	private static void login(WebDriver driver, String s, String ss) {
		// Click on Math Calculators
//			driver.findElement(By.xpath("//*[@id=\"username\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(s);
//			driver.findElement(By.xpath("//*[@id=\"password\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(ss);

		driver.findElement(By.xpath("/html/body/div[5]/div[3]/div[2]/div/div/form[1]/ul/li[3]/button[1]")).click();
		driver.navigate().to("https://myline-eon.ro/facturile-mele");
	}

	private static void getInvoicePrint(WebDriver driver, int i) {
		String sss = "//*[@id=\"select-trigger\"]";
		String locConsum = driver.findElement(By.xpath(sss)).getText();
		myPrint(locConsum);

		String s2 = "/html/body/div[5]/div[4]/div[2]/div/div[2]/div[4]/div/div/div[2]/div/div[2]/h4";
		String plata = driver.findElement(By.xpath(s2)).getText();
		myPrint(plata);

		String invoiceTableXPath = "//*[@id=\"invoices\"]";
		String tabelPlati = driver.findElement(By.xpath(invoiceTableXPath)).getText();
		myPrint(tabelPlati);

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//Now you can do whatever you need to do with it, for example copy somewhere
		try {
			FileUtils.copyFile(scrFile, new File("output/screenshot" + i + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param driver
	 * @param rowNumber TODO
	 * @param rowNumber starts from 1
	 */
	private static void switchTable(WebDriver driver, int rowNumber) {
		{
			assert rowNumber > 0;
			String sss = "//*[@id=\"select-trigger\"]";
			driver.findElement(By.xpath(sss)).click();

			driver.findElement(By.xpath("//ul[@id='select-list']/li[" + rowNumber + "]/a/span[2]")).click();
		}
	}
}