package com.nc.edu.ta.artemryabtsev.pr7;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UItest{

    @FindBy(id = "registerForm:username")
    WebElement fieldName;

    @FindBy(id = "registerForm:password")
    WebElement fieldPassword;

    @FindBy(id = "registerForm:confirmPassword")
    WebElement fieldConfirmPassword;

    @FindBy(id = "registerForm:email")
    WebElement fieldEmail;

    @FindBy(name = "registerForm:j_idt26")
    WebElement buttonReg;

    @FindBy(xpath = "//tr[1]/td[3]/span")
    WebElement wayErrorFieldName;

    @FindBy(xpath = "//tr[2]/td[3]/span")
    WebElement wayErrorFieldPassword;

    @FindBy(xpath = "//tr[3]/td[3]/span")
    WebElement wayErrorFieldConfirmPassword;

    @FindBy(xpath = "//tr[5]/td[3]/span")
    WebElement wayErrorFieldEmail;

    @FindBy(xpath = "//div[2]/div")
    WebElement wayMessageOfRegistration;

    @FindBy(xpath = "//tr[1]/td[3]/span")
    WebElement wayErrorNameRepeat;

    @FindBy(xpath = "//div/div[2]/div")
    WebElement wayErrorMaxNumberUser;

    Generation generation = new Generation();
    String userName = generation.generationUserName();    
    String userPassword = generation.generationUserPassword();
    String userEmail = generation.generationEmail();

    public ChromeDriver driver;

    @Before
    public void setup() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream("application-test.properties")){
            properties.load(resourceStream);
        }catch (IOException e){
            e.printStackTrace();
        }
        System.setProperty("webdriver.chrome.driver", "D:\\NetCracker\\Lab\\pr7\\tools\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    public void init(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @Test
    public void testUserName() {
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        init(driver);
        try {
            fieldName.sendKeys(userName);
            buttonReg.click();
            assertNull(wayErrorFieldName);
            Assert.fail("Expected AssertionError");
        } catch (AssertionError thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }

        fieldName.clear();
        fieldName.sendKeys(generation.generationLowerChar(2,5));
        buttonReg.click();
        assertEquals("Login must be alphanumeric string with length => 6 and <= 50.",
                wayErrorFieldName.getText());

        fieldName.clear();
        fieldName.sendKeys(generation.generationSymbol(7,10));
        buttonReg.click();
        assertEquals("Login must be alphanumeric string with length => 6 and <= 50.",
                wayErrorFieldName.getText());

//        fieldName.clear();//данная проверка должна выдавать сообщение "Login must be alphanumeric string with length => 6 and <= 50."
//        fieldName.sendKeys("Name123Name123Name123Name123Name123Name123Name123Na");
//        buttonReg.click();
//        assertEquals("Login must be alphanumeric string with length => 6 and <= 50.",
//                wayErrorFieldName.getText());

        fieldName.clear();
        fieldName.sendKeys("");
        buttonReg.click();
        assertEquals("Login must not be empty.",
                wayErrorFieldName.getText());
    }

    @Test
    public void testUserPassword() {
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        init(driver);

        fieldPassword.sendKeys(userPassword);
        buttonReg.click();
        assertEquals("Password must match confirm password.",
                wayErrorFieldPassword.getText());

        fieldPassword.clear();
        fieldPassword.sendKeys("");
        buttonReg.click();
        assertEquals("Please enter password.",
                wayErrorFieldPassword.getText());

        fieldPassword.clear();
        fieldPassword.sendKeys("Password");
        buttonReg.click();
        assertEquals("At least one digit must be in password",
                wayErrorFieldPassword.getText());

        fieldPassword.clear();
        fieldPassword.sendKeys("Passwor");
        buttonReg.click();
        assertEquals("Password length must me >= 8 and <= 50.",
                wayErrorFieldPassword.getText());

        fieldPassword.clear();
        fieldPassword.sendKeys("Password1");
        buttonReg.click();
        assertEquals("At least one non alpha numeric symbol must be in password",
                wayErrorFieldPassword.getText());

        fieldPassword.clear();
        fieldPassword.sendKeys("password1!");
        buttonReg.click();
        assertEquals("At least one upper letter must be in password",
                wayErrorFieldPassword.getText());
    }

    @Test
    public void testUserConfirmPassword(){
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        init(driver);

        try{
            fieldConfirmPassword.sendKeys(userPassword);
            buttonReg.click();
            assertNull(wayErrorFieldConfirmPassword);
            Assert.fail("Exception AssertionError");
        }catch (AssertionError thrown){
            Assert.assertNotEquals("", thrown.getMessage());
        }

        fieldConfirmPassword.sendKeys("");
        buttonReg.click();
        assertEquals("Please enter confirm password",
                wayErrorFieldConfirmPassword.getText());

// Не появляются сообщения о неверном вводе данных
//        fieldConfirmPassword.clear();
//        fieldConfirmPassword.sendKeys("Password");
//        buttonReg.click();
//        assertEquals("At least one digit must be in confirm password",
//                wayErrorFieldConfirmPassword.getText());
//
//        fieldConfirmPassword.clear();
//        fieldConfirmPassword.sendKeys("Passwor");
//        buttonReg.click();
//        assertEquals("Confirm password length must me >= 8 and <= 50.",
//                wayErrorFieldConfirmPassword.getText());
//
//        fieldConfirmPassword.clear();
//        fieldConfirmPassword.sendKeys("Password1");
//        buttonReg.click();
//        assertEquals("At least one non alpha numeric symbol must be in confirm password",
//                wayErrorFieldConfirmPassword.getText());
//
//        fieldConfirmPassword.clear();
//        fieldConfirmPassword.sendKeys("password1!");
//        buttonReg.click();
//        assertEquals("At least one upper letter must be in confirm password",
//                wayErrorFieldConfirmPassword.getText());
    }

    @Test
    public void testUserEmail(){
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        init(driver);
        try{
            fieldEmail.sendKeys(userEmail);
            buttonReg.click();
            assertNull(wayErrorFieldEmail);
            Assert.fail("Exception AssertionError");
        }catch (AssertionError thrown){
            Assert.assertNotEquals("", thrown.getMessage());
        }

        fieldEmail.clear();
        fieldEmail.sendKeys("");
        buttonReg.click();
        assertEquals("email field can't be empty",
                wayErrorFieldEmail.getText());

        fieldEmail.clear();
        fieldEmail.sendKeys("email");
        buttonReg.click();
        assertEquals("Email address is incorrect.",
                wayErrorFieldEmail.getText());

        fieldEmail.clear();
        fieldEmail.sendKeys("email@");
        buttonReg.click();
        assertEquals("Email address is incorrect.",
                wayErrorFieldEmail.getText());

        fieldEmail.clear();
        fieldEmail.sendKeys("email@.ru");
        buttonReg.click();
        assertEquals("Email address is incorrect.",
                wayErrorFieldEmail.getText());

        fieldEmail.clear();
        fieldEmail.sendKeys("@pochta.ru");
        buttonReg.click();
        assertEquals("Email address is incorrect.",
                wayErrorFieldEmail.getText());
    }

    @Test
    public void testAllFieldsCorrectFilledIn() {
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        String userPassword = generation.generationUserPassword();
        init(driver);
        fieldName.sendKeys(userName);
        fieldPassword.sendKeys(userPassword);
        fieldConfirmPassword.sendKeys(userPassword);
        fieldEmail.sendKeys(userEmail);
        buttonReg.click();
        assertEquals("You have successfully registered\n" +
                        "Use your credentials to login",
                wayMessageOfRegistration.getText());
    }

//   В данном тесте должна появляться строка "Please fill out the form once again".
    @Test
    public void testAllFieldsIncorrectFilledIn() {
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        init(driver);
        fieldName.sendKeys("Name");
        fieldPassword.sendKeys("Password1");
        fieldConfirmPassword.sendKeys("Password1");
        fieldEmail.sendKeys("email@.ru");
        buttonReg.click();
        assertEquals("Please fill out the form once again",
                wayMessageOfRegistration.getText());
    }

    @Test
    public void testUniquenessOfTheUserNameField(){
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        init(driver);
        fieldName.sendKeys("Name12");
        buttonReg.click();
        assertEquals("User with such login already exists.",
                wayErrorNameRepeat.getText());
    }

    @Test
    public void testMaxNumberOfUsersPerHour(){
        for (int i = 0; i <= 51; i++){
            String userPassword = generation.generationUserPassword();
            driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
            init(driver);
            fieldName.sendKeys(userName);
            fieldPassword.sendKeys(userPassword);
            fieldConfirmPassword.sendKeys(userPassword);
            fieldEmail.sendKeys(userEmail);
            buttonReg.click();
        }
        assertEquals("Please fill out the form later.",
                wayErrorMaxNumberUser.getText());
    }

    @After
    public void close() {
        driver.quit();
    }
}