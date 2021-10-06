package com.nc.edu.ta.artemryabtsev.pr7;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UItest {

    public ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\NetCracker\\Lab\\pr7\\tools\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testUserName() {
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        driver.findElement(By.id("registerForm:username")).sendKeys("Name");
        driver.findElement(By.name("registerForm:j_idt26")).click();
        assertEquals("Login must be alphanumeric string with length => 6 and <= 50.",
                driver.findElement(By.xpath("//tr[1]/td[3]/span[@class]")).getText());

        driver.findElement(By.id("registerForm:username")).clear();
        driver.findElement(By.id("registerForm:username")).sendKeys("!@#$%^&*()");
        driver.findElement(By.name("registerForm:j_idt26")).click();
        assertEquals("Login must be alphanumeric string with length => 6 and <= 50.",
                driver.findElement(By.xpath("//tr[1]/td[3]/span[@class]")).getText());

        driver.findElement(By.id("registerForm:username")).clear();
        driver.findElement(By.id("registerForm:username")).sendKeys("Name123Name123Name123Name123Name123Name123Name123Na");
        driver.findElement(By.name("registerForm:j_idt26")).click();
        assertEquals("Login must be alphanumeric string with length => 6 and <= 50.",
                driver.findElement(By.xpath("//tr[1]/td[3]/span[@class]")).getText());

        driver.findElement(By.id("registerForm:username")).clear();
        driver.findElement(By.id("registerForm:username")).sendKeys("");
        driver.findElement(By.name("registerForm:j_idt26")).click();
        assertEquals("Login must not be empty.",
                driver.findElement(By.xpath("//tr[1]/td[3]/span[@class]")).getText());
    }

    @Test
    public void testUserPassword() {
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        driver.findElement(By.id("registerForm:password")).sendKeys("");
        driver.findElement(By.name("registerForm:j_idt26")).click();
        assertEquals("Please enter password.",
                driver.findElement(By.xpath("//tr[2]/td[3]/span[@class]")).getText());

        driver.findElement(By.id("registerForm:password")).clear();
        driver.findElement(By.id("registerForm:password")).sendKeys("Password");
        driver.findElement(By.name("registerForm:j_idt26")).click();
        assertEquals("At least one digit must be in password",
                driver.findElement(By.xpath("//tr[2]/td[3]/span[@class]")).getText());

        driver.findElement(By.id("registerForm:password")).clear();
        driver.findElement(By.id("registerForm:password")).sendKeys("Passwor");
        driver.findElement(By.name("registerForm:j_idt26")).click();
        assertEquals("Password length must me >= 8 and <= 50.",
                driver.findElement(By.xpath("//tr[2]/td[3]/span[@class]")).getText());

        driver.findElement(By.id("registerForm:password")).clear();
        driver.findElement(By.id("registerForm:password")).sendKeys("Password1");
        driver.findElement(By.name("registerForm:j_idt26")).click();
        assertEquals("At least one non alpha numeric symbol must be in password",
                driver.findElement(By.xpath("//tr[2]/td[3]/span[@class]")).getText());

        driver.findElement(By.id("registerForm:password")).clear();
        driver.findElement(By.id("registerForm:password")).sendKeys("password1!");
        driver.findElement(By.name("registerForm:j_idt26")).click();
        assertEquals("At least one upper letter must be in password",
                driver.findElement(By.xpath("//tr[2]/td[3]/span[@class]")).getText());
    }

    @Test
    public void testUserConfirmPassword() {
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        driver.findElement(By.id("registerForm:confirmPassword")).sendKeys("");
        driver.findElement(By.name("registerForm:j_idt26")).click();
        assertEquals("Please enter confirm password",
                driver.findElement(By.xpath("//tr[3]/td[3]/span[@class]")).getText());

        driver.findElement(By.id("registerForm:confirmPassword")).clear();
        driver.findElement(By.id("registerForm:confirmPassword")).sendKeys("Password");
        driver.findElement(By.name("registerForm:j_idt26")).click();
        assertEquals("At least one digit must be in confirm password",
                driver.findElement(By.xpath("//tr[3]/td[3]/span[@class]")).getText());

        driver.findElement(By.id("registerForm:confirmPassword")).clear();
        driver.findElement(By.id("registerForm:confirmPassword")).sendKeys("Passwor");
        driver.findElement(By.name("registerForm:j_idt26")).click();
        assertEquals("Confirm password length must me >= 8 and <= 50.",
                driver.findElement(By.xpath("//tr[3]/td[3]/span[@class]")).getText());

        driver.findElement(By.id("registerForm:confirmPassword")).clear();
        driver.findElement(By.id("registerForm:confirmPassword")).sendKeys("Password1");
        driver.findElement(By.name("registerForm:j_idt26")).click();
        assertEquals("At least one non alpha numeric symbol must bёёёe in confirm password",
                driver.findElement(By.xpath("//tr[3]/td[3]/span[@class]")).getText());

        driver.findElement(By.id("registerForm:confirmPassword")).clear();
        driver.findElement(By.id("registerForm:confirmPassword")).sendKeys("password1!");
        driver.findElement(By.name("registerForm:j_idt26")).click();
        assertEquals("At least one upper letter must be in confirm password",
                driver.findElement(By.xpath("//tr[3]/td[3]/span[@class]")).getText());
    }

    @Test
    public void testUserEmail() {
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        driver.findElement(By.id("registerForm:email")).sendKeys("");
        driver.findElement(By.name("registerForm:j_idt26")).click();
        assertEquals("email field can't be empty",
                driver.findElement(By.xpath("//tr[5]/td[3]/span[@class]")).getText());

        driver.findElement(By.id("registerForm:email")).clear();
        driver.findElement(By.id("registerForm:email")).sendKeys("email");
        driver.findElement(By.name("registerForm:j_idt26")).click();
        assertEquals("Email address is incorrect.",
                driver.findElement(By.xpath("//tr[5]/td[3]/span[@class]")).getText());

        driver.findElement(By.id("registerForm:email")).clear();
        driver.findElement(By.id("registerForm:email")).sendKeys("email@");
        driver.findElement(By.name("registerForm:j_idt26")).click();
        assertEquals("Email address is incorrect.",
                driver.findElement(By.xpath("//tr[5]/td[3]/span[@class]")).getText());

        driver.findElement(By.id("registerForm:email")).clear();
        driver.findElement(By.id("registerForm:email")).sendKeys("email@.ru");
        driver.findElement(By.name("registerForm:j_idt26")).click();
        assertEquals("Email address is incorrect.",
                driver.findElement(By.xpath("//tr[5]/td[3]/span[@class]")).getText());

        driver.findElement(By.id("registerForm:email")).clear();
        driver.findElement(By.id("registerForm:email")).sendKeys("@pochta.ru");
        driver.findElement(By.name("registerForm:j_idt26")).click();
        assertEquals("Email address is incorrect.",
                driver.findElement(By.xpath("//tr[5]/td[3]/span[@class]")).getText());
    }

    @Test
    public void testAllFieldsCorrectFilledIn() {
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        driver.findElement(By.id("registerForm:username")).sendKeys("Name12");
        driver.findElement(By.id("registerForm:password")).sendKeys("Password1!");
        driver.findElement(By.id("registerForm:confirmPassword")).sendKeys("Password1!");
        driver.findElement(By.id("registerForm:email")).sendKeys("email@pochta.ru");
        driver.findElement(By.name("registerForm:j_idt26")).click();
        assertEquals("You have successfully registered\n" +
                        "Use your credentials to login",
                driver.findElement(By.xpath("/html/body/div/div[2]/div[@class]")).getText());
    }

    @Test
    public void testAllFieldsIncorrectFilledIn() {
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        driver.findElement(By.id("registerForm:username")).sendKeys("Name");
        driver.findElement(By.id("registerForm:password")).sendKeys("Password1");
        driver.findElement(By.id("registerForm:confirmPassword")).sendKeys("Password1");
        driver.findElement(By.id("registerForm:email")).sendKeys("email@.ru");
        driver.findElement(By.name("registerForm:j_idt26")).click();
        assertEquals("You have successfully registered\n" +
                        "Use your credentials to login",
                driver.findElement(By.xpath("/html/body/div/div[2]/div[@class]")).getText());
    }
    @Test
    public void testUniquenessOfTheUserNameField(){
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        driver.findElement(By.id("registerForm:username")).sendKeys("Name12");
        driver.findElement(By.name("registerForm:j_idt26")).click();
        assertEquals("User with such login already exists.",
                driver.findElement(By.xpath("//tr[1]/td[3]/span[@class]")).getText());
    }

    @Test
    public void testMaxNumberOfUsersPerHour(){
        JsonSimpleParser parser = new JsonSimpleParser();
        Root root = parser.parser();
        List listUsers = root.getUsers();
        for (Object list: listUsers){
            Users users = (Users) list;
            driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
            driver.findElement(By.id("registerForm:username")).sendKeys(users.getName());
            driver.findElement(By.id("registerForm:password")).sendKeys(users.getPassword());
            driver.findElement(By.id("registerForm:confirmPassword")).sendKeys(users.getConfirmPassword());
            driver.findElement(By.id("registerForm:email")).sendKeys(users.getEmail());
            driver.findElement(By.name("registerForm:j_idt26")).click();
            assertEquals("Please fill out the form later.",
                    driver.findElement(By.xpath("//div/div[2]/div[@class]")).getText());
        }
    }


    @After
    public void close() {
        driver.quit();
    }
}