package aetna;

import Utils.DriverUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.aetnaPortalPage;
import com.github.javafaker.Faker;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class aetnaPortal {
    WebDriver driver;
    JavascriptExecutor js;
    aetnaPortalPage aetnaPage;



    @BeforeClass
    void SetUp() {
        driver = DriverUtil.getWebDriver();
        //instantiating page objects
        aetnaPage = new aetnaPortalPage();
        driver.get(aetnaPage.quoteUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(16));
    }

    @Test(priority = 1)
    void navigateToLoginPage() throws InterruptedException{


        driver.findElement(aetnaPage.individualDentalLocator).click();
        driver.findElement(aetnaPage.loginLocator).click();
        driver.findElement(aetnaPage.zipCodeLocator).sendKeys("14211");
        driver.findElement(aetnaPage.emailLocator).sendKeys("abdr@gmail.com");

        DriverUtil.scrollDown();
        Thread.sleep(4000);
        String actualText = driver.findElement(aetnaPage.actualTextLocator).getText();
        //System.out.println(actualText);
        Assert.assertTrue(actualText.contains("Why choose Aetna"));
        driver.findElement(aetnaPage.findAPlanLocator).click();
    }
    @Test(priority = 2)
    void aboutYou() throws InterruptedException, IOException, CsvValidationException {
        String absolutePath = "/Users/abide/IdeaProjects/SeleniumWinter/TestData/MOCK_DATA.csv";
        CSVReader csvReader = new CSVReader( new FileReader( absolutePath ) );
        String[] cells = csvReader.readNext();

        driver.findElement(aetnaPage.firstNameLocator).sendKeys( cells[0].split(" ")[0] );              //("Abu");

        driver.findElement(aetnaPage.MILocator).sendKeys("M");
        driver.findElement(aetnaPage.lastNameLocator).sendKeys(cells[0].split(" ")[1] );               //("Musa");
        Thread.sleep(2000);
        driver.findElement(aetnaPage.genderLocator).sendKeys( cells[2] );     //("Female");

        driver.findElement(aetnaPage.DOBLocator).sendKeys( cells[3] );        //("01/01/2001");
        driver.findElement(aetnaPage.optionLocator).sendKeys("I had");
        DriverUtil.waitAndClick(aetnaPage.nextButtonLocator);
    }
    @Test(priority = 3)
    void pickYourPlan() throws InterruptedException {
        driver.findElement(aetnaPage.plansLocator).click();
        driver.findElement(aetnaPage.plansNextLocator).click();

    }
    @Test(priority = 4)
    void contact() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(aetnaPage.homeAddressLocator).sendKeys("553 Walden Ave");
        driver.findElement(aetnaPage.aptLocator).sendKeys("#2");
        driver.findElement(aetnaPage.cityLocator).sendKeys("Buffalo");
        driver.findElement(aetnaPage.confirmEmailLocator).sendKeys("abdr@gmail.com");
        Faker faker = new Faker();
        String phoneNumber = faker.numerify("##########");

        driver.findElement(aetnaPage.primaryPhoneLocator).sendKeys(phoneNumber);     //("1234567890");
        Thread.sleep(5000);

        driver.findElement(aetnaPage.phoneTypeLocator).sendKeys("Mobile");

        driver.findElement(aetnaPage.carrierLocator).sendKeys("Metro Plus");
        driver.findElement(aetnaPage.residentLocator).click();

        driver.findElement(aetnaPage.readAndWriteLocator).click();

        driver.findElement(aetnaPage.accountabilityLocator).click();

        DriverUtil.waitAndClick(aetnaPage.nextLocator);



    }



    @AfterClass
    void wrapUp() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}

