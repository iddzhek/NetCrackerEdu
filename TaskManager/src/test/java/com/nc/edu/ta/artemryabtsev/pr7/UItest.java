package com.nc.edu.ta.artemryabtsev.pr7;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class UItest {

    public ChromeDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testUserName(){
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
//        driver.findElement(By.id("registerForm:username")).sendKeys("Name12");
        driver.findElement(By.id("registerForm:username")).sendKeys("Name12345");
//        assertEquals("Name12", driver.findElement(By.id("registerForm:username")));
        driver.findElement(By.id("registerForm:password")).sendKeys("Name12345");
        driver.findElement(By.id("registerForm:confirmPassword")).sendKeys("Name12345");
        driver.findElement(By.id("registerForm:email")).sendKeys("Name12345");
        driver.findElement(By.name("registerForm:j_idt26")).click();
    }

//    @After
//    public void close(){
//        driver.quit();
//    }
}
