package com.weborder;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Order {
	
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "/Users/iambekzhan/Documents/Selenium/Drivers/chromedriver");
		
		WebDriver driver = new ChromeDriver();
		
		String url = "http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx";
		
		String user = "Tester";
		String password = "test";
		
		Random r = new Random();
		
		// generating zip code from 01000 till 99999
		int zipCode = r.nextInt(99999) + 1000;
		String zip = "";
		if(zipCode >= 10000) {
			zip = zip + zipCode;
		}else {
			zip = "0" + zipCode;
		}
		
		// creating random numbers from 1 to 100 for quantity
		int ran = r.nextInt(101)+1;
		String randomNumbers = "" + ran;
		
		// pick random letters for middle name
		int ranLet = r.nextInt(25);	
		String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String middle = "" + capitalLetters.charAt(ranLet);
		
		// take to the website
		driver.get(url);
		
		// enter user name and password, then click Login button
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys(user);
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys(password);
		driver.findElement(By.id("ctl00_MainContent_login_button")).click();
		
		// after entering to home page put Order button on the left side
		driver.findElement(By.linkText("Order")).click();

		// in Order page filling up product boxes
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(Keys.BACK_SPACE);;
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(randomNumbers);
		
		// in Order page filling up address information
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys("John " + middle + " Smith");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).sendKeys("123 Any st");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys("Anytown");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4")).sendKeys("Virginia");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(zip);
		
		// in Order page filling up payment information
		for(int i = 0; i < 2; i++) {
			int num = r.nextInt(3);
			if(num == 0) {
				driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_0")).click();
				driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys("4" + r.nextInt(99999999) + "" + r.nextInt(9999999));
				break;
			}
			if(num == 1) {
				driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_1")).click();
				driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys("5" + r.nextInt(99999999) + "" + r.nextInt(9999999));
				break;
			}
			if(num == 2){
				driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_2")).click();
				driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys("3" + r.nextInt(99999999) + "" + r.nextInt(999999));
				break;
			}
		}
		
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).sendKeys("03/21");
		
		// click on process button in order page
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
		
		// checking that order has been successfully added
		String text = driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder\"]/tbody/tr/td/div/strong")).getText();
		if(text.equals("New order has been successfully added.")) {
			System.out.println("Your order succesfully added");
		}else {
			System.out.println("Your order didn't added");
		}
		
		// after checking that order successfully added just quit browser
		Thread.sleep(5000);
		driver.quit();
		
		
	}
}
