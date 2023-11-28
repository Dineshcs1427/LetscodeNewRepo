package com.BaseClass;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

public class LaunchBrowser {
	 static WebDriver driver;

	public void openBrowser() throws InterruptedException {

		System.setProperty("webdriver.chorme", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		// ChromeDriver cd = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://letcode.in/");
		// driver.close();

	}

	public void Login() throws IOException {
		driver.findElement(By.linkText("Log in")).click();
		driver.findElement(By.name("email")).sendKeys("dineshrt90@gmail.com");
		driver.findElement(By.name("password")).sendKeys("Qwer@123");
		driver.findElement(By.xpath("//button[text()='LOGIN']")).click();
		String title = driver.getTitle();
		System.out.println(title);

	}
	
	//validation
	
	/*
	 * ISdislayed(); isselected(); isenabled();
	 */

	public void Logout() {

		driver.findElement(By.xpath("//a[text()='Sign out']")).click();

	}

	public void takescreenshot() throws Throwable {
		// take screen shot of visble page
		File Sourcefile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File desc = new File("./snaps/img.png");
		FileHandler.copy(Sourcefile, desc);

		// take screen shot of particular element
		WebElement ele = driver.findElement(By.xpath("//button[text()='LOGIN']"));
		File elesrc = ((TakesScreenshot) ele).getScreenshotAs(OutputType.FILE);
		File eledesc = new File("./snaps/img2.png");
		FileHandler.copy(elesrc, eledesc);

	}

	// Alerts
	// Simple alert
	public void alert() {
		// simple alert
		driver.findElement(By.xpath("//button[text()='LOGIN']")).click();
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		alert.accept();

		// conform alert
		// alert.dismiss();

		// prompt alert
		driver.findElement(By.xpath("//button[text()='LOGIN']")).click();
		driver.switchTo().alert();
		alert.sendKeys("test1");
		alert.accept();

		/// Un handled alert exception / No alert handle expetion occur if alert is not
		/// handles
	}

	// Frame
	// exception -> nosuchframeexception
	public void frame() {

		driver.switchTo().frame(0);// by index
		driver.switchTo().frame("testframe");// by name/id
		WebElement frame = driver.findElement(By.xpath("//iframe[text(0,'testframe')]"));// by name/id
		driver.switchTo().frame(frame);// by web element
		// move to parent frame - one frame before the cureent frame
		driver.switchTo().parentFrame();
		// move to main window
		driver.switchTo().defaultContent();

	}
	
	//Dragand drop actions
	//"cotectclick used for right click 
	
  public void Action1()
  {
	  WebElement alert = driver.findElement(By.xpath("//iframe[text(0,'testframe')]"));// by name/id
	  
	  Actions ele = new Actions(driver);
	  ele.click(alert).perform();
	  ele.contextClick().perform();
	  
  }
  public void slectet()
  {
	  WebElement alert = driver.findElement(By.xpath("//iframe[text(0,'testframe')]"));// by name/id
	  
	  Select ele = new Select(alert);
	  ele.selectByIndex(0);
	  ele.selectByValue("test");
	  ele.selectByVisibleText("present in list");
	  WebElement text= ele.getFirstSelectedOption();
	  System.out.println(text.getText());
	  //multi select 
	  
	  ele.selectByIndex(0);
	  ele.selectByValue("test");
	  ele.deselectByIndex(0);
	  ele.deselectAll();
  }
	

//Main method
	public static void main(String[] args) throws Throwable {
		LaunchBrowser Lb = new LaunchBrowser();
		Lb.openBrowser();
		Lb.Login();
		Lb.takescreenshot();
		// Lb.Logout();
		driver.close();

	}

}
