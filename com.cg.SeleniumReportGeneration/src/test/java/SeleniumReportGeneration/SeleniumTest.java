import java.io.File;
import java.io.IOException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumTest {
static String url = "http://18.237.88.206:9000/petclinic-Dev/owners/find.html";
public static WebDriver driver;
        private static int errorCode = 0;
        private static int fail = 0;
        private static int totalCases = 0;
        private static int pass = 0;
        private static int acceptable = 60;
        private static float passPercentage;
        private static float failPercentage;

@BeforeTest
public static void launchBrowser() throws InterruptedException {
System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
ChromeOptions chromeOptions = new ChromeOptions();
chromeOptions.addArguments("--headless");
chromeOptions.addArguments("--no-sandbox");
driver = new ChromeDriver(chromeOptions);                
driver.get(url );
Thread.sleep(1000);
        }

@Test
public static void testCaseOne() throws InterruptedException, IOException {
totalCases++;
driver.findElement(By.xpath("//a[@class='btn btn-default']")).click();
Thread.sleep(1000);
driver.findElement(By.xpath("//input[@id='firstName']")).clear();
driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Yuva");
driver.findElement(By.xpath("//input[@id='lastName']")).clear();
driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Liza");
driver.findElement(By.xpath("//input[@id='address']")).clear();
driver.findElement(By.xpath("//input[@id='address']")).sendKeys("456 Clark Street");
driver.findElement(By.xpath("//input[@id='city']")).clear();
driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Chicago");
driver.findElement(By.xpath("//input[@id='telephone']")).clear();
driver.findElement(By.xpath("//input[@id='telephone']")).sendKeys("3452987654");
driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
System.out.println("user addition successful !");
driver.navigate().to(url);
Thread.sleep(1000);
driver.findElement(By.xpath("//input[@id='lastName']")).clear();
driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Liza");
driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
WebElement name	= driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]"));
String expectedValue = name.getText();
System.out.println(expectedValue);
String actualValue = "Yuva Liza";
Assert.assertEquals(actualValue, expectedValue);
		}

@Test
public static void testCaseTwo() throws InterruptedException {
        totalCases++;
        Thread.sleep(1000);
        if (driver.getPageSource().contains("Last name")) {
        //if (driver.getPageSource().contains("Yashaswini")) {
        System.out.println("TEXT IS PRESENT ON THE WEB PAGE");
        pass++;
        }
        else {
  System.out.println("ERROR WHILE FINDING THE TEXT - Failed");
        errorCode = 2;
        fail++;
        }

}

@Test
private static void testCaseThree() throws InterruptedException {
        totalCases++;
        JavascriptExecutor js = (JavascriptExecutor)driver;
        driver.findElement(By.xpath("//span[contains(text(),'Find owners')]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Find Owner')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Carlos Estaban')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Edit Owner')]")).click();
        js.executeScript("document.getElementById('city').value='London'");
        driver.findElement(By.xpath("//button[contains(text(),'Update Owner')]")).click();
        WebElement city = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/table[1]/tbody[1]/tr[3]/td[1]"));
//                                      String outputValue1 = city.getText();
//                                      if (outputValue1.contentEquals("London")) {
//                                              System.out.println("Test Passed!");
//                                              pass++;
//                                      } else {
//                                              System.out.println("Test Failed");
//                                              errorCode = 2;
//                                              fail++;
//                                      }
        String expectedValue = city.getText();
        //String actualValue = "new york";
        String actualValue = "London";
         Assert.assertEquals(actualValue, expectedValue);


}


        @AfterTest
         public void terminateBrowser(){
        driver.quit();
    }
}

