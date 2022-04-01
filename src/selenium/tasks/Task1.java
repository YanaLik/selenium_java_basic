package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import static org.junit.Assert.*;

public class Task1 {
    WebDriver driver;

    @Before
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/enter_a_number");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void errorOnText() throws InterruptedException {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        String textToEnter = "someText";
        driver.findElement(By.id("numb")).sendKeys(textToEnter);
        driver.findElement(By.tagName("button")).click();
        System.out.println(driver.findElement(By.id("ch1_error")).getText());
        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
        String smallNumberToEnter = "45";
        driver.findElement(By.id("numb")).sendKeys(smallNumberToEnter);
        driver.findElement(By.tagName("button")).click();
        System.out.println(driver.findElement(By.id("ch1_error")).getText());
        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        String bigNumberToEnter = "455";
        driver.findElement(By.id("numb")).sendKeys(bigNumberToEnter);
        driver.findElement(By.tagName("button")).click();
        System.out.println(driver.findElement(By.id("ch1_error")).getText());
        assertEquals("Number is too big", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void correctSquareRootWithoutRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        String validNumberToEnter = "64";
        driver.findElement(By.id("numb")).sendKeys(validNumberToEnter);
        driver.findElement(By.tagName("button")).click();

        Alert correctAlert = driver.switchTo().alert();
        String expectedMessage = "Square root of 64 is 8.00";
        String actualMessage = correctAlert.getText();
        assertEquals(expectedMessage,actualMessage);
        correctAlert.accept();

        String expectedErrorMessage = "";
        String actualErrorMessage = driver.findElement(By.id("ch1_error")).getText();
        assertEquals(expectedErrorMessage,actualErrorMessage);



    }

    @Test
    public void correctSquareRootWithRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        String validNumberToEnter = "65";
        driver.findElement(By.id("numb")).sendKeys(validNumberToEnter);
        driver.findElement(By.tagName("button")).click();


        Alert correctAlert = driver.switchTo().alert();
        String expectedMessage = "Square root of 65 is 8.06";
        String actualMessage = correctAlert.getText();
        assertEquals(expectedMessage,actualMessage);
        correctAlert.accept();

        String expectedErrorMessage2 = "";
        String actualErrorMessage2 = driver.findElement(By.id("ch1_error")).getText();
        assertEquals(expectedErrorMessage2,actualErrorMessage2);

    }
}
