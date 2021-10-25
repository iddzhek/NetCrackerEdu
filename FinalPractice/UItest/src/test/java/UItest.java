import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import static org.junit.Assert.assertEquals;

public class UItest {

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

    @FindBy(xpath = "//*[@id=\"registerForm:role\"]/option[1]")
    WebElement listSelectionRoleRead;

    @FindBy(xpath = "//*[@id=\"registerForm:role\"]/option[2]")
    WebElement listSelectionRoleReadWrite;

    @FindBy(xpath = "//*[@id=\"registerForm:role\"]/option[3]")
    WebElement listSelectionRoleAdmin;

    @FindBy(xpath = "//*[@id=\"registerForm\"]/table/tbody/tr[1]/td[3]/label")
    WebElement tipUserName;

    @FindBy(xpath = "//*[@id=\"registerForm\"]/table/tbody/tr[2]/td[3]/label")
    WebElement tipUserPassword;

    @FindBy(xpath = "//*[@id=\"registerForm\"]/table/tbody/tr[3]/td[3]/label")
    WebElement tipUserConfirmPassword;

    @FindBy(xpath = "//*[@id=\"registerForm\"]/table/tbody/tr[5]/td[3]/label")
    WebElement tipUserEmail;

    @FindBy(xpath = "//*[@id=\"registerForm\"]/table/tbody/tr[6]/td[3]/label")
    WebElement tipRole;

    public ChromeDriver driver;

    Generation generation = new Generation();
    String userName = generation.generationUserName();
    String userPassword = generation.generationUserPassword();
    String userEmail = generation.generationEmail();

    @Before
    public void setup() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream("application-test.properties")){
            properties.load(resourceStream);
        }catch (IOException e){
            e.printStackTrace();
        }
        System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriverchrome.path"));
        driver = new ChromeDriver();
    }

    public void init(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void clearField(){
        fieldName.clear();
        fieldPassword.clear();
        fieldConfirmPassword.clear();
        fieldEmail.clear();
    }

    @Test
    public void testNewUser(){

        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        init(driver);
        fieldName.sendKeys(generation.generationUserName());
        fieldPassword.sendKeys(userPassword);
        fieldConfirmPassword.sendKeys(userPassword);
        listSelectionRoleAdmin.click();
        fieldEmail.sendKeys(generation.generationEmail());
        buttonReg.click();
        assertEquals("You have successfully registered\n" +
                "Use your credentials to login",
                wayMessageOfRegistration.getText());


        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        fieldName.sendKeys(generation.generationUserName());
        fieldPassword.sendKeys(userPassword);
        fieldConfirmPassword.sendKeys(userPassword);
        listSelectionRoleReadWrite.click();
        fieldEmail.sendKeys(generation.generationEmail());
        buttonReg.click();
        assertEquals("You have successfully registered\n" +
                        "Use your credentials to login",
                wayMessageOfRegistration.getText());

        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        fieldName.sendKeys(generation.generationUserName());
        fieldPassword.sendKeys(userPassword);
        fieldConfirmPassword.sendKeys(userPassword);
        listSelectionRoleRead.click();
        fieldEmail.sendKeys(generation.generationEmail());
        buttonReg.click();
        assertEquals("You have successfully registered\n" +
                        "Use your credentials to login",
                wayMessageOfRegistration.getText());
    }

    @Test
    public void testIncorrectUsernamePasswordEmail() {
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        init(driver);
        try {
            fieldName.sendKeys("Userr");
            fieldPassword.sendKeys("Password4");
            fieldConfirmPassword.sendKeys("Password");
            fieldEmail.sendKeys("userr5@.com");
            buttonReg.click();
            assertEquals("Login must be alphanumeric string with length => 6 and <= 50.",
                    wayErrorFieldName.getText());
            assertEquals("At least one non alpha numeric symbol must be in password",
                    wayErrorFieldPassword.getText());
            assertEquals("The Repeat password must be the same as the Password",
                    wayErrorFieldConfirmPassword.getText());
            assertEquals("Email address is incorrect.",
                    wayErrorFieldEmail.getText());
        }catch (NoSuchElementException thrown){
            System.out.println(thrown.getMessage());
            assertEquals(false, true);
        }

        try{
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        fieldName.sendKeys(generation.generationNumbers(1, 5));
        fieldPassword.sendKeys("Password+");
        fieldConfirmPassword.sendKeys("Password");
        fieldEmail.sendKeys("userr-6testmail.com");
        buttonReg.click();
        assertEquals("Login must be alphanumeric string with length => 6 and <= 50.",
                wayErrorFieldName.getText());
        assertEquals("At least one digit must be in password",
                wayErrorFieldPassword.getText());
        assertEquals("The Repeat password must be the same as the Password",
                wayErrorFieldConfirmPassword.getText());
            assertEquals("Email address is incorrect.",
                    wayErrorFieldEmail.getText());
        }catch (NoSuchElementException thrown){
            System.out.println(thrown.getMessage());
            assertEquals(false, true);
        }

        try{
            driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
            fieldName.sendKeys("User7");
            fieldPassword.sendKeys("Password4+");
            fieldConfirmPassword.sendKeys("Password");
            fieldEmail.sendKeys("userr.7testmail.com");
            buttonReg.click();
            assertEquals("Login must be alphanumeric string with length => 6 and <= 50.",
                    wayErrorFieldName.getText());
            assertEquals("Password must match confirm password.",
                    wayErrorFieldPassword.getText());
            assertEquals("The Repeat password must be the same as the Password",
                    wayErrorFieldConfirmPassword.getText());
            assertEquals("Email address is incorrect.",
                    wayErrorFieldEmail.getText());
        }catch (NoSuchElementException thrown){
            System.out.println(thrown.getMessage());
            assertEquals(false, true);
        }

        try{
            driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
            fieldName.sendKeys("User8");
            fieldPassword.sendKeys("Passwor");
            fieldConfirmPassword.sendKeys("Password");
            fieldEmail.sendKeys("userr_testmail.");
            buttonReg.click();
            assertEquals( "Login must be alphanumeric string with length => 6 and <= 50.",
                    wayErrorFieldName.getText());
            assertEquals("Password length must me >= 8 and <= 50.",
                    wayErrorFieldPassword.getText());
            assertEquals("The Repeat password must be the same as the Password",
                    wayErrorFieldConfirmPassword.getText());
            assertEquals("Email address is incorrect.",
                    wayErrorFieldEmail.getText());
        }catch (NoSuchElementException thrown){
            System.out.println(thrown.getMessage());
            assertEquals(false, true);
        }
    }

    @Test
    public void testFieldQuestion(){
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        init(driver);
        try{
            assertEquals( "Login must contain only alphanumeric characters and contain at least 6 characters",
                    tipUserName.getAttribute("title"));
            assertEquals("The Password must have at least 8 characters, at least 1 digit," +
                            " at least 1 lower case letter, at least 1 upper case letter, at least 1 non-alphanumeric character",
                    tipUserPassword.getAttribute("title"));
            assertEquals("The Repeat password must be the same as the Password",
                    tipUserConfirmPassword.getAttribute("title"));
            assertEquals("Enter here valid e-mail address",
                    tipUserEmail.getAttribute("title"));
            assertEquals("Pick corresponding role",
                    tipRole.getAttribute("title"));
        }catch (NoSuchElementException thrown){
            System.out.println(thrown.getMessage());
            assertEquals(false, true);
        }
    }

    @Test
    public void testExistingUserName(){
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        init(driver);
        try{
            fieldName.sendKeys(userName);
            fieldPassword.sendKeys(userPassword);
            fieldConfirmPassword.sendKeys(userPassword);
            fieldEmail.sendKeys(userEmail);
            buttonReg.click();
            assertEquals("You have successfully registered\n" +
                            "Use your credentials to login",
                    wayMessageOfRegistration.getText());
        }catch (NoSuchElementException thrown){
            System.out.println(thrown.getMessage());
            assertEquals(false, true);
        }

        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
        init(driver);
        try{
            fieldName.sendKeys(userName);
            fieldPassword.sendKeys(userPassword);
            fieldConfirmPassword.sendKeys(userPassword);
            fieldEmail.sendKeys(userEmail);
            buttonReg.click();
            assertEquals( "User with such login already exists.",
                    wayErrorNameRepeat.getText());
            assertEquals( "User with such email already exists.",
                    wayErrorFieldEmail.getText());
        }catch (NoSuchElementException thrown){
            System.out.println(thrown.getMessage());
            assertEquals(false, true);
        }
    }

    @After
    public void close(){
        driver.quit();
    }
}

