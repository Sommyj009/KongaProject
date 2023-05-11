import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class KongaOrderingFlowTest {

    private WebDriver driver;

    @BeforeTest
    public void start () throws InterruptedException {
        //Locate where the chrome driver is
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        Thread.sleep(200);
        //Open your chrome browser
        driver = new ChromeDriver();
        Thread.sleep(200);

        //Test 1. Input the Konga URL
        driver.get("https://www.konga.com/");
        Thread.sleep(5000);

        //Maximise the browser
        driver.manage().window().maximize();
        Thread.sleep(1000);
    }

        @Test (priority = 0)
        public void signIn () throws InterruptedException {
            //Test 2. Sign in to Konga Successfully
            //Click the login button
            driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/div[4]/a")).click();
            Thread.sleep(500);
            //Input Email address or Phone Number
            driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("");
            Thread.sleep(500);
            //Input Password in the password field
            driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("");
            Thread.sleep(500);
            //Click the Login button
            driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
            Thread.sleep(10000);
        }

        @Test(priority = 1)
        public void addItemToCart () throws InterruptedException {
            //Test 3. From the Categories, click on the “Computers and Accessories”
            driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[2]/div/a[2]")).click();
            Thread.sleep(5000);

            //Test 4. Click on the Laptop SubCategory
            driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[3]/a/label/span")).click();
            Thread.sleep(5000);

            //Test 5. Click on the Apple MacBooks
            driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[3]/a/ul/li[1]/a/label/span")).click();
            Thread.sleep(3000);

            //Test 6. Add an item to the cart
            //Click on a preferred laptop
            driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/section/section/section/ul/li[6]/div/div/div[2]/form/div[3]/button")).click();
            Thread.sleep(5000);
            //Click My Cart button
            driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/a[2]")).click();
            Thread.sleep(10000);
            //Click Checkout button
            driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[3]/section/section/aside/div[3]/div/div[2]/button")).click();
            Thread.sleep(10000);
    }

        @Test (priority = 2)
        public void selectAddress () throws InterruptedException {
            //Test 7. Select Address
            //Click the Change button
            driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[1]/div[2]/div/button")).click();
            Thread.sleep(10000);
            //Click the Add Delivery Address button
            driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[2]/div[1]/div[2]/div[1]/div/button")).click();
            Thread.sleep(1000);
            //Select an address
            driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[2]/div/div/div[2]/div[1]/form/button")).click();
            Thread.sleep(5000);
            //Click the Use this Address button
            driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[3]/div/div/div/a")).click();
            Thread.sleep(10000);
        }

        @Test (priority = 3)
        public void paymentFlow () throws InterruptedException {
            //Test 8. Continue to make payment
            //Select the Pay Now Payment Option
            driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[1]/div[1]/span/input")).click();
            Thread.sleep(5000);
            //Click the Continue to Payment button
            driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[3]/div[2]/div/button")).click();
            Thread.sleep(10000);
            //Switch to iframe using the id of the frame
            driver.switchTo().frame("kpg-frame-component");
            Thread.sleep(5000);

            //Test 9. Select the Card Payment Method
            driver.findElement(By.xpath("//*[@id=\"channel-template\"]/div[2]/div/div[2]/button")).click();
            Thread.sleep(5000);

            //Test 10. Input Invalid Card Details
            //input card number
            driver.findElement(By.xpath("//*[@id=\"card-number\"]")).sendKeys("5399223467857986");
            Thread.sleep(500);
            //input expiry date
            driver.findElement(By.xpath("//*[@id=\"expiry\"]")).sendKeys("1024");
            Thread.sleep(500);
            //input CVV
            driver.findElement(By.xpath("//*[@id=\"cvv\"]")).sendKeys("223");
            Thread.sleep(500);
            //input card pin
            driver.findElement(By.xpath("//*[@id=\"card-pin-new\"]")).sendKeys("45327");
            Thread.sleep(500);
            //Click the Pay Now button
            driver.findElement(By.xpath("//*[@id=\"validateCardForm\"]")).click();
            Thread.sleep(10000);

            //Test 11. Print Out the error message: Invalid card number
            String actualMsg = driver.findElement(By.xpath("//*[@id=\"card-number_unhappy\"]")).getText();
            String errorMsg = "Invalid card number";
            if (actualMsg.equals(errorMsg))
                //Pass
                System.out.println("Correct Error Message");
            else
                //Fail
                System.out.println("Wrong Error Message");
            Thread.sleep(3000);

            //Test 12. Close the iFrame that displays the input card Modal
            driver.switchTo().defaultContent();
            Thread.sleep(5000);
        }

        @AfterTest
        public void closeBrowser() {
            //Test 13. Quit the Browser
            driver.quit();
            }
}
