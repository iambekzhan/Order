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
		int ran = r.nextInt(100);
		int ranLet = r.nextInt(25);		
		int zipCode = r.nextInt(99999) + 10000;
		
		String randomNumbers = "" + ran;
		String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String middle = "";
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 1; i++) {
            middle = middle + capitalLetters.charAt(ranLet);
		}
		String zip = "" + zipCode;
		
		driver.get(url);
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys(user);
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys(password);
		driver.findElement(By.id("ctl00_MainContent_login_button")).click();
		driver.findElement(By.linkText("Order")).click();
		
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(Keys.BACK_SPACE);;
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(randomNumbers);
		
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys("John " + middle + " Smith");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).sendKeys("123 Any st");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys("Anytown");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4")).sendKeys("Virginia");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(zip);
		
		for(int i = 0; i < 2; i++) {
			int num = r.nextInt(2);
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
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
		
		String text = driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder\"]/tbody/tr/td/div/strong")).getText();
		if(text.equals("New order has been successfully added.")) {
			System.out.println("Your order succesfully added");
		}else {
			System.out.println("Your order didn't added");
		}
		
		Thread.sleep(5000);
		driver.quit();
		
		
	}
}
