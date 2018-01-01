package com.myProject;

import org.apache.commons.lang3.StringEscapeUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Screen;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;

public class MyProgram {
    static WebDriver driver;

    static Screen screen = new Screen();

    public static void sendMessage() throws InterruptedException, AWTException {
        String companyName;
        Actions action = new Actions(driver);
        WebElement profileName = driver.findElement(By.xpath("//h1[contains(@class, 'pv-top-card-section__name')]"));

        //Double click
        action.doubleClick(profileName).perform();

        try{
            screen.find("F://bot_image/double_click.PNG");
            System.out.println("Message button clicked");
        }catch(Exception e){System.out.println("double click image not found");}

//Right Click
        action.contextClick(profileName).perform();
System.exit(0);

        try {
            companyName = driver.findElement(By.xpath("//h3[contains(@class, 'pv-top-card-section__company')]")).getText();
        }catch(Exception e){
            companyName = "your organization";
        }

        if(companyName.matches(".*\\bfreelance\\b.*") || companyName.matches(".*\\bFreelance\\b.*") || companyName.matches(".*\\bFreelancer\\b.*") || companyName.matches(".*\\bfreelancer\\b.*") || companyName.matches(".*\\bself\\b.*") || companyName.matches(".*\\bmyself\\b.*") || companyName.matches(".*\\bMyself\\b.*")){
            companyName = "job";
        }

//        String[] splitedName = profileName.split("\\s+");
//        String firstName = splitedName[0];
//
//        String capPersonName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);

        //driver.findElement(By.xpath("//button[contains(@class, 'message-anywhere-button')]")).click();

        try{
            screen.click("F://bot_image/linkedin-message.PNG");
            System.out.println("Message button clicked");
        }catch(Exception e){System.out.println("Compose button image not found");}

        Thread.sleep(1000);

        driver.findElement(By.xpath("//textarea[contains(@name, 'message')]")).click();

//        driver.findElement(By.xpath("//textarea[contains(@name, 'message')]")).sendKeys(
//                "Hi "+capPersonName+","
//                        + "\n\nThank you for accepting request. :)"
//                        + "\n\nJust wanted to ask you something related to "+companyName+"? May I?"
//                        + "\n\n-Priya");
        try{
            screen.click("F://bot_image/linkedin_msg_text.png");
            System.out.println("linkedin msg text clicked");
        }catch(Exception e){System.out.println("Compose button image not found");}

        //screen.type("Hi "+capPersonName+", \n\nThank you for accepting my request. :)\n\nJust wanted to ask you something related to "+companyName+". May I?\n\nPlease let me know.\n\nSincerely,\nRajendra Arora");

        try{
            screen.click("F://bot_image/linkedin_msg_btn.png");
            System.out.println("linkedin msg btn clicked");
        }catch(Exception e){System.out.println("Compose button image not found");}

        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(@data-control-name, 'overlay.close_conversation_window')]")).click();
        Thread.sleep(2000);
    }

    public static void main(String[] args) throws InterruptedException, AWTException {

        ChromeOptions chromeOptions = new ChromeOptions();
        File chromeDriver = new File("C://chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
//		chromeOptions.addArguments("--headless");
//		chromeOptions.addArguments("--disable-gpu");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.linkedin.com/");
        driver.findElement(By.xpath("//input[contains(@class, 'login-email')]")).sendKeys("anuragarora1045@gmail.com");
        driver.findElement(By.xpath("//input[contains(@class, 'login-password')]")).sendKeys("Bhawanku16");
        driver.findElement(By.xpath("//input[contains(@id, 'login-submit')]")).click();
        Thread.sleep(5000);

        driver.get("https://www.linkedin.com/mynetwork/invite-connect/connections/");

        Thread.sleep(5000);

        List<WebElement> allCards = driver.findElements(By.xpath("//li[contains(@class, 'connection-card')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;


        /*---------------------------------------------------*/
        int counter = 12;
        /*---------------------------------------------------*/

        for (WebElement allChilds : allCards){
            counter++;

            try{
                /*Scroll to view element*/
                WebElement element = driver.findElement(By.xpath("//li[contains(@class, 'connection-card')]["+(counter-1)+"]"));
                js.executeScript("arguments[0].scrollIntoView()", element);
                Thread.sleep(2000);
            }catch(Exception e){
                System.out.println("No need to scroll.");
            }

            driver.findElement(By.xpath("//li[contains(@class, 'connection-card')]["+counter+"]")).click();

            Thread.sleep(3000);

            /*Sending message.*/
            sendMessage();

            Thread.sleep(8000);
            System.out.println("Counter: "+counter);

            /*Go back to previous connection page.*/
            js.executeScript("window.history.go(-1)");
            Thread.sleep(5000);

            System.out.println("done");
        }

        Thread.sleep(10000);
        driver.quit();
    }
}
