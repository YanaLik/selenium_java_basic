package selenium.sample;

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

import static org.junit.Assert.*;

public class Sample7Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/actions";

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // declaration above:
        driver = new ChromeDriver();
        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void selectCheckBox() throws Exception {
//         TODO:
//        check that none of the checkboxes are ticked
        WebElement checkBoxOpt1 = driver.findElement(By.id("vfb-6-0"));
        WebElement checkBoxOpt2 = driver.findElement(By.id("vfb-6-1"));
        WebElement checkBoxOpt3 = driver.findElement(By.id("vfb-6-2"));
        assertFalse(checkBoxOpt1.isSelected());
        assertFalse(checkBoxOpt2.isSelected());
        assertFalse(checkBoxOpt3.isSelected());

//        tick  "Option 2"
        checkBoxOpt2.click();
//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        assertFalse(checkBoxOpt1.isSelected());
        assertFalse(checkBoxOpt3.isSelected());
        assertTrue(checkBoxOpt2.isSelected());
//        tick  "Option 3"
        checkBoxOpt3.click();
//        click result
        driver.findElement(By.id("result_button_checkbox")).click();
//        check that text 'You selected value(s): Option 2, Option 3' is being displayed
        assertEquals("You selected value(s): Option 2, Option 3",driver.findElement(By.id("result_checkbox")).getText());
    }


    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
//        check that none of the radio are selected
        WebElement radioBoxOpt1 = driver.findElement(By.id("vfb-7-1"));
        WebElement radioBoxOpt2 = driver.findElement(By.id("vfb-7-2"));
        WebElement radioBoxOpt3 = driver.findElement(By.id("vfb-7-3"));
//        select  "Option 3"
        radioBoxOpt3.click();
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertFalse(radioBoxOpt1.isSelected());
        assertFalse(radioBoxOpt2.isSelected());
        assertTrue(radioBoxOpt3.isSelected());
//        select  "Option 1"
        radioBoxOpt1.click();
//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertFalse(radioBoxOpt2.isSelected());
        assertFalse(radioBoxOpt3.isSelected());
        assertTrue(radioBoxOpt1.isSelected());
//        click result
        driver.findElement(By.id("result_button_ratio")).click();
//        check that 'You selected option: Option 1' text is being displayed
        assertEquals("You selected option: Option 1",driver.findElement(By.id("result_radio")).getText());
    }

    @Test
    public void selectOption() throws Exception {
//        select "Option 3" in Select
        WebElement dropdown = driver.findElement(By.id("vfb-12"));
        Select dropdownSelect = new Select(dropdown);
        List<WebElement> allSelections;
        dropdownSelect.selectByVisibleText("Option 3");
//        check that selected option is "Option 3"
        allSelections = dropdownSelect.getAllSelectedOptions();
        assertEquals(1,allSelections.size());
        assertEquals("Option 3", allSelections.get(0).getText());
//        select "Option 2" in Select
        dropdownSelect.selectByVisibleText("Option 2");
//        check that selected option is "Option 2"
        allSelections = dropdownSelect.getAllSelectedOptions();
        assertEquals(1,allSelections.size());
        assertEquals("Option 2", allSelections.get(0).getText());
//        click result
        driver.findElement(By.id("result_button_select")).click();
//        check that 'You selected option: Option 2' text is being displayed
        assertEquals("You selected option: Option 2", driver.findElement(By.id("result_select")).getText());
    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//        enter date '4 of July 2007' using calendar widget
//        check that correct date is added
        driver.findElement(By.id("ui-datepicker-div")).click();

    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//        enter date '2 of May 1959' using text
//        check that correct date is added
    }
}
