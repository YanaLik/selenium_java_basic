package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

public class Task2 {
    WebDriver driver;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all field are empty and no tick are clicked

           WebElement boxName = driver.findElement(By.id("fb_name"));
           assertEquals("",driver.findElement(By.id("fb_name")).getText());

           WebElement boxAge = driver.findElement(By.id("fb_age"));
           assertEquals("",driver.findElement(By.id("fb_age")).getText());

           WebElement boxLangEng = driver.findElement(By.xpath("//*[@id='lang_check']/*[2]"));
           assertFalse(boxLangEng.isSelected());

           WebElement boxLangFr = driver.findElement(By.xpath("//*[@id='lang_check']/*[4]"));
           assertFalse(boxLangFr.isSelected());

           WebElement boxLangSp = driver.findElement(By.xpath("//*[@id='lang_check']/*[6]"));
           assertFalse(boxLangSp.isSelected());
           WebElement boxLangCh = driver.findElement(By.xpath("//*[@id='lang_check']/*[8]"));
           assertFalse(boxLangCh.isSelected());

           WebElement radioBoxM = driver.findElement(By.xpath("//input[@class='w3-radio' and @value='male']"));
           assertFalse(radioBoxM.isSelected());
           WebElement radioBoxF = driver.findElement(By.xpath("//input[@class='w3-radio' and @value='female']"));
           assertFalse(radioBoxF.isSelected());


           WebElement dropdown = driver.findElement(By.id("like_us"));
           Select dropdownSelect = new Select(dropdown);
           List<WebElement> allSelections;
           dropdownSelect.selectByVisibleText("Choose your option");
           allSelections = dropdownSelect.getAllSelectedOptions();
           assertEquals(1,allSelections.size());
           assertEquals("Choose your option", allSelections.get(0).getText());

          WebElement boxComment = driver.findElement(By.xpath("//*[@name='comment']"));
          assertEquals("",driver.findElement(By.xpath("//*[@name='comment']")).getText());


//         "Don't know" is selected in "Genre"
           WebElement radioBoxN = driver.findElement(By.xpath("//input[@class='w3-radio' and @value='']"));
           assertTrue(radioBoxN.isSelected());

//         "Choose your option" in "How do you like us?"
           assertEquals("Choose your option", allSelections.get(0).getText());
//         check that the button send is blue with white letters
        WebElement button = driver.findElement(By.xpath("//*[@type='submit']"));
        assertEquals("rgba(33, 150, 243, 1)",driver.findElement(By.xpath("//*[@type='submit']")).getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)",driver.findElement(By.xpath("//*[@type='submit']")).getCssValue("text-decoration-color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
           driver.findElement(By.xpath("//*[@type='submit']")).click();
//         check fields are empty or null
        assertEquals("",driver.findElement(By.id("name")).getText());
        assertEquals("",driver.findElement(By.id("age")).getText());
        assertEquals("",driver.findElement(By.id("language")).getText());
        assertEquals("null",driver.findElement(By.id("gender")).getText());
        assertEquals("null",driver.findElement(By.id("option")).getText());
        assertEquals("",driver.findElement(By.id("comment")).getText());
//         check button colors
//         (green with white letter and red with white letters)
        WebElement buttonGreen = driver.findElement(By.xpath("//*[@class='w3-btn w3-green w3-xlarge']"));
        assertEquals("rgba(76, 175, 80, 1)",driver.findElement(By.xpath("//*[@class='w3-btn w3-green w3-xlarge']")).getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)",driver.findElement(By.xpath("//*[@class='w3-btn w3-green w3-xlarge']")).getCssValue("text-decoration-color"));

        WebElement buttonRed = driver.findElement(By.xpath("//*[@class='w3-btn w3-red w3-xlarge']"));
        assertEquals("rgba(244, 67, 54, 1)",driver.findElement(By.xpath("//*[@class='w3-btn w3-red w3-xlarge']")).getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)",driver.findElement(By.xpath("//*[@class='w3-btn w3-red w3-xlarge']")).getCssValue("text-decoration-color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        driver.findElement(By.id("fb_name")).sendKeys("TEST");
        driver.findElement(By.id("fb_age")).sendKeys("35");
        driver.findElement(By.xpath("//*[@id='lang_check']/*[2]")).click();
        driver.findElement(By.xpath("//input[@class='w3-radio' and @value='female']")).click();

        WebElement dropdown = driver.findElement(By.id("like_us"));
        Select dropdownSelect = new Select(dropdown);
        List<WebElement> allSelections;
        dropdownSelect.selectByVisibleText("Good");
        driver.findElement(By.xpath("//*[@name='comment']")).sendKeys("no comments as of today");
        driver.findElement(By.xpath("//*[@type='submit']")).click();
//         check fields are filled correctly
        assertEquals("TEST",driver.findElement(By.id("name")).getText());
        assertEquals("35",driver.findElement(By.id("age")).getText());
        assertEquals("English",driver.findElement(By.id("language")).getText());
        assertEquals("female",driver.findElement(By.id("gender")).getText());
        assertEquals("Good",driver.findElement(By.id("option")).getText());
        assertEquals("no comments as of today",driver.findElement(By.id("comment")).getText());

//         check button colors
//         (green with white letter and red with white letters)
        WebElement buttonGreen = driver.findElement(By.xpath("//*[@class='w3-btn w3-green w3-xlarge']"));
        assertEquals("rgba(76, 175, 80, 1)",driver.findElement(By.xpath("//*[@class='w3-btn w3-green w3-xlarge']")).getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)",driver.findElement(By.xpath("//*[@class='w3-btn w3-green w3-xlarge']")).getCssValue("text-decoration-color"));

        WebElement buttonRed = driver.findElement(By.xpath("//*[@class='w3-btn w3-red w3-xlarge']"));
        assertEquals("rgba(244, 67, 54, 1)",driver.findElement(By.xpath("//*[@class='w3-btn w3-red w3-xlarge']")).getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)",driver.findElement(By.xpath("//*[@class='w3-btn w3-red w3-xlarge']")).getCssValue("text-decoration-color"));

    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        driver.findElement(By.id("fb_name")).sendKeys("TEST");
//         click "Send"
        driver.findElement(By.xpath("//*[@type='submit']")).click();
//         click "Yes"
        driver.findElement(By.xpath("//*[@class='w3-btn w3-green w3-xlarge']")).click();
//         check message text: "Thank you, NAME, for your feedback!"
        assertEquals("Thank you, TEST, for your feedback!",driver.findElement(By.id("message")).getText());
//         color of text is white with green on the background
        assertEquals("rgba(255, 255, 255, 1)",driver.findElement(By.id("message")).getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)",driver.findElement(By.xpath("//*[contains(@class,'w3-panel w3-green')]")).getCssValue("background-color"));

    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        driver.findElement(By.xpath("//*[@type='submit']")).click();
//         click "Yes"
        driver.findElement(By.xpath("//*[@class='w3-btn w3-green w3-xlarge']")).click();
//         check message text: "Thank you for your feedback!"
        assertEquals("Thank you for your feedback!",driver.findElement(By.id("message")).getText());
//         color of text is white with green on the background
        assertEquals("rgba(255, 255, 255, 1)",driver.findElement(By.id("message")).getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)",driver.findElement(By.xpath("//*[contains(@class,'w3-panel w3-green')]")).getCssValue("background-color"));

    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        driver.findElement(By.id("fb_name")).sendKeys("TEST");
        driver.findElement(By.id("fb_age")).sendKeys("35");
        driver.findElement(By.xpath("//*[@id='lang_check']/*[2]")).click();
        driver.findElement(By.xpath("//input[@class='w3-radio' and @value='female']")).click();

        WebElement dropdown = driver.findElement(By.id("like_us"));
        Select dropdownSelect = new Select(dropdown);
        List<WebElement> allSelections;
        dropdownSelect.selectByVisibleText("Good");
        driver.findElement(By.xpath("//*[@name='comment']")).sendKeys("no comments as of today");

//         click "Send"
        driver.findElement(By.xpath("//*[@type='submit']")).click();
//         click "No"
        driver.findElement(By.xpath("//*[@class='w3-btn w3-red w3-xlarge']")).click();
//         check fields are filled correctly

        String placeholderValueName = driver.findElement(By.id("fb_name")).getAttribute("placeholder");
        String expectedPlaceholderValueName = "Name";
        assertEquals(expectedPlaceholderValueName,driver.findElement(By.id("fb_name")).getAttribute("placeholder"));

        String placeholderValueAge = driver.findElement(By.id("fb_age")).getAttribute("placeholder");
        String expectedPlaceholderValueAge = "Age";
        assertEquals(expectedPlaceholderValueName,driver.findElement(By.id("fb_name")).getAttribute("placeholder"));


        String placeholderValueLang = driver.findElement(By.xpath("//*[@id='lang_check']/*[2]")).getAttribute("value");
        String expectedPlaceholderValueLang = "English";
        assertEquals(expectedPlaceholderValueLang,driver.findElement(By.xpath("//*[@id='lang_check']/*[2]")).getAttribute("value"));

        String placeholderValueGen = driver.findElement(By.xpath("//input[@class='w3-radio' and @value='female']")).getAttribute("value");
        String expectedPlaceholderValueGen = "female";
        assertEquals(expectedPlaceholderValueGen,driver.findElement(By.xpath("//input[@class='w3-radio' and @value='female']")).getAttribute("value"));

        String placeholderValueSelect = driver.findElement(By.id("like_us")).getAttribute("value");
        String expectedPlaceholderValueSelect = "Good";
        assertEquals(expectedPlaceholderValueSelect, driver.findElement(By.id("like_us")).getAttribute("value"));

        String placeholderValueComment = driver.findElement(By.xpath("//*[@name='comment']")).getAttribute("value");
        String expectedPlaceholderComment = "no comments as of today";
        assertEquals(expectedPlaceholderComment, driver.findElement(By.xpath("//*[@name='comment']")).getAttribute("value"));

    }
}
