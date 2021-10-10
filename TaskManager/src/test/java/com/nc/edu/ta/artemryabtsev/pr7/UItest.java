package com.nc.edu.ta.artemryabtsev.pr7;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UItest {

    String userName = "registerForm:username";
    String userPassword = "registerForm:password";
    String confirmPassword = "registerForm:confirmPassword";
    String email = "registerForm:email";
    String registerForm = "registerForm:j_idt26";

    Generation gen = new Generation();
    public ChromeDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "D:\\NetCracker\\Lab\\pr7\\tools\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testUserName() throws NoSuchElementException {
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        try {
            driver.findElement(By.id(userName)).sendKeys(gen.generationUserName());
            driver.findElement(By.name(registerForm)).click();
            assertNull(driver.findElement(By.xpath("//tr[1]/td[3]/span")));
            Assert.fail("Expected NoSuchElementException");
        } catch (NoSuchElementException thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }

        driver.findElement(By.id(userName)).clear();
        driver.findElement(By.id(userName)).sendKeys("Name");
        driver.findElement(By.name(registerForm)).click();
        assertEquals("Login must be alphanumeric string with length => 6 and <= 50.",
                driver.findElement(By.xpath("//tr[1]/td[3]/span")).getText());

        driver.findElement(By.id(userName)).clear();
        driver.findElement(By.id(userPassword)).sendKeys("!@#$%^&*()");
        driver.findElement(By.name(confirmPassword)).click();
        assertEquals("Login must be alphanumeric string with length => 6 and <= 50.",
                driver.findElement(By.xpath("//tr[1]/td[3]/span")).getText());

        driver.findElement(By.id("registerForm:username")).clear();//данная проверка должна выдавать сообщение "Login must be alphanumeric string with length => 6 and <= 50."
        driver.findElement(By.id("registerForm:username")).sendKeys("Name123Name123Name123Name123Name123Name123Name123Na");
        driver.findElement(By.name("registerForm:j_idt26")).click();
        assertEquals("Login must be alphanumeric string with length => 6 and <= 50.",
                driver.findElement(By.xpath("//tr[1]/td[3]/span")).getText());

        driver.findElement(By.id(userName)).clear();
        driver.findElement(By.id(confirmPassword)).sendKeys("");
        driver.findElement(By.name(registerForm)).click();
        assertEquals("Login must not be empty.",
                driver.findElement(By.xpath("//tr[1]/td[3]/span")).getText());
    }

    @Test
    public void testUserPassword() throws NoSuchElementException{
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        driver.findElement(By.id(userPassword)).sendKeys(gen.generationUserPassword());
        driver.findElement(By.name(registerForm)).click();
        assertEquals("Password must match confirm password.",
                driver.findElement(By.xpath("//tr[2]/td[3]/span")).getText());

        driver.findElement(By.id(userPassword)).clear();
        driver.findElement(By.id(userPassword)).sendKeys("");
        driver.findElement(By.name(registerForm)).click();
        assertEquals("Please enter password.",
                driver.findElement(By.xpath("//tr[2]/td[3]/span")).getText());

        driver.findElement(By.id(userPassword)).clear();
        driver.findElement(By.id(userPassword)).sendKeys("Password");
        driver.findElement(By.name(registerForm)).click();
        assertEquals("At least one digit must be in password",
                driver.findElement(By.xpath("//tr[2]/td[3]/span")).getText());

        driver.findElement(By.id(userPassword)).clear();
        driver.findElement(By.id(userPassword)).sendKeys("Passwor");
        driver.findElement(By.name(registerForm)).click();
        assertEquals("Password length must me >= 8 and <= 50.",
                driver.findElement(By.xpath("//tr[2]/td[3]/span")).getText());

        driver.findElement(By.id(userPassword)).clear();
        driver.findElement(By.id(userPassword)).sendKeys("Password1");
        driver.findElement(By.name(registerForm)).click();
        assertEquals("At least one non alpha numeric symbol must be in password",
                driver.findElement(By.xpath("//tr[2]/td[3]/span")).getText());

        driver.findElement(By.id(userPassword)).clear();
        driver.findElement(By.id(userPassword)).sendKeys("password1!");
        driver.findElement(By.name(registerForm)).click();
        assertEquals("At least one upper letter must be in password",
                driver.findElement(By.xpath("//tr[2]/td[3]/span")).getText());
    }

    @Test
    public void testUserConfirmPassword() throws NoSuchElementException{
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        try{
            driver.findElement(By.id(confirmPassword)).sendKeys(gen.generationUserPassword());
            driver.findElement(By.name(registerForm)).click();
            assertNull(driver.findElement(By.xpath("//tr[3]/td[3]/span")));
            Assert.fail("Exception NoSuchElementException");
        }catch (NoSuchElementException thrown){
            Assert.assertNotEquals("", thrown.getMessage());
        }

        driver.findElement(By.id(confirmPassword)).sendKeys("");
        driver.findElement(By.name(registerForm)).click();
        assertEquals("Please enter confirm password",
                driver.findElement(By.xpath("//tr[3]/td[3]/span")).getText());

// Не появляются сообщения о неверном вводе данных
//        driver.findElement(By.id("registerForm:confirmPassword")).clear();
//        driver.findElement(By.id("registerForm:confirmPassword")).sendKeys("Password");
//        driver.findElement(By.name("registerForm:j_idt26")).click();
//        assertEquals("At least one digit must be in confirm password",
//                driver.findElement(By.xpath("//tr[3]/td[3]/span")).getText());
//
//        driver.findElement(By.id("registerForm:confirmPassword")).clear();
//        driver.findElement(By.id("registerForm:confirmPassword")).sendKeys("Passwor");
//        driver.findElement(By.name("registerForm:j_idt26")).click();
//        assertEquals("Confirm password length must me >= 8 and <= 50.",
//                driver.findElement(By.xpath("//tr[3]/td[3]/span")).getText());
//
//        driver.findElement(By.id("registerForm:confirmPassword")).clear();
//        driver.findElement(By.id("registerForm:confirmPassword")).sendKeys("Password1");
//        driver.findElement(By.name("registerForm:j_idt26")).click();
//        assertEquals("At least one non alpha numeric symbol must be in confirm password",
//                driver.findElement(By.xpath("//tr[3]/td[3]/span")).getText());
//
//        driver.findElement(By.id("registerForm:confirmPassword")).clear();
//        driver.findElement(By.id("registerForm:confirmPassword")).sendKeys("password1!");
//        driver.findElement(By.name("registerForm:j_idt26")).click();
//        assertEquals("At least one upper letter must be in confirm password",
//                driver.findElement(By.xpath("//tr[3]/td[3]/span")).getText());
    }

    @Test
    public void testUserEmail() throws NoSuchElementException{
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        try{
            driver.findElement(By.id(email)).sendKeys(gen.generationEmail());
            driver.findElement(By.name(registerForm)).click();
            assertNull(driver.findElement(By.xpath("//tr[5]/td[3]/span")));
            Assert.fail("Exception NoSuchElementException");
        }catch (NoSuchElementException thrown){
            Assert.assertNotEquals("", thrown.getMessage());
        }

        driver.findElement(By.id(email)).clear();
        driver.findElement(By.id(email)).sendKeys("");
        driver.findElement(By.name(registerForm)).click();
        assertEquals("email field can't be empty",
                driver.findElement(By.xpath("//tr[5]/td[3]/span")).getText());

        driver.findElement(By.id(email)).clear();
        driver.findElement(By.id(email)).sendKeys("email");
        driver.findElement(By.name(registerForm)).click();
        assertEquals("Email address is incorrect.",
                driver.findElement(By.xpath("//tr[5]/td[3]/span")).getText());

        driver.findElement(By.id(email)).clear();
        driver.findElement(By.id(email)).sendKeys("email@");
        driver.findElement(By.name(registerForm)).click();
        assertEquals("Email address is incorrect.",
                driver.findElement(By.xpath("//tr[5]/td[3]/span")).getText());

        driver.findElement(By.id(email)).clear();
        driver.findElement(By.id(email)).sendKeys("email@.ru");
        driver.findElement(By.name(registerForm)).click();
        assertEquals("Email address is incorrect.",
                driver.findElement(By.xpath("//tr[5]/td[3]/span")).getText());

        driver.findElement(By.id(email)).clear();
        driver.findElement(By.id(email)).sendKeys("@pochta.ru");
        driver.findElement(By.name(registerForm)).click();
        assertEquals("Email address is incorrect.",
                driver.findElement(By.xpath("//tr[5]/td[3]/span")).getText());
    }

    @Test
    public void testAllFieldsCorrectFilledIn() {
        String password = gen.generationUserPassword();
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        driver.findElement(By.id(userName)).sendKeys(gen.generationUserName());
        driver.findElement(By.id(userPassword)).sendKeys(password);
        driver.findElement(By.id(confirmPassword)).sendKeys(password);
        driver.findElement(By.id(email)).sendKeys("email@pochta.ru");
        driver.findElement(By.name(registerForm)).click();
        assertEquals("You have successfully registered\n" +
                        "Use your credentials to login",
                driver.findElement(By.xpath("//div[2]/div")).getText());
    }

//   В данном тесте должна появляться строка "Please fill out the form once again".
    @Test
    public void testAllFieldsIncorrectFilledIn() {
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        driver.findElement(By.id(userName)).sendKeys("Name");
        driver.findElement(By.id(userPassword)).sendKeys("Password1");
        driver.findElement(By.id(confirmPassword)).sendKeys("Password1");
        driver.findElement(By.id(email)).sendKeys("email@.ru");
        driver.findElement(By.name(registerForm)).click();
        assertEquals("Please fill out the form once again",
                driver.findElement(By.xpath("//div[2]/div")).getText());
    }

    @Test
    public void testUniquenessOfTheUserNameField(){
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        driver.findElement(By.id(userName)).sendKeys("Name12");
        driver.findElement(By.name(registerForm)).click();
        assertEquals("User with such login already exists.",
                driver.findElement(By.xpath("//tr[1]/td[3]/span")).getText());
    }

    @Test
    public void testMaxNumberOfUsersPerHour(){
        for (int i = 0; i <= 51; i++){
            String password = gen.generationUserPassword();
            driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
            driver.findElement(By.id(userName)).sendKeys(gen.generationUserName());
            driver.findElement(By.id(userPassword)).sendKeys(password);
            driver.findElement(By.id(confirmPassword)).sendKeys(password);
            driver.findElement(By.id(email)).sendKeys(gen.generationEmail());
            driver.findElement(By.name(registerForm)).click();
        }
        assertEquals("Please fill out the form later.",
                driver.findElement(By.xpath("//div/div[2]/div")).getText());
    }

    @After
    public void close() {
        driver.quit();
    }
}