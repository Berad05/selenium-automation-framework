package coms.vsb.tests;

import coms.vsb.listener.TestListener;
import coms.vsb.util.Config;
import coms.vsb.util.Constants;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Listeners({TestListener.class})
public class AbstractTest {
    protected WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);
    private static Capabilities capabilities;

    @BeforeSuite
    public void setUpConfig(){
        Config.initialize();
    }
    @BeforeTest()
    public void setUp(ITestContext ctx) throws MalformedURLException, URISyntaxException {

        this.driver=Boolean.getBoolean(Constants.GRID_ENABLED) ? getRemoteWebDriver():getLocalDriver();
        ctx.setAttribute(Constants.driver,this.driver);
        this.driver.manage().window().maximize();


    }

    public WebDriver getLocalDriver(){
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
         return new ChromeDriver(options);


    }

    public WebDriver getRemoteWebDriver() throws MalformedURLException, URISyntaxException {
        capabilities = new ChromeOptions();
        if(Config.get(Constants.BROWSER).equalsIgnoreCase(Constants.FIREFOX)){
            capabilities = new FirefoxOptions();

        }
        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat, hubHost);
        log.info("Grid url: {}",url );


        return new RemoteWebDriver(new URI(url).toURL(),capabilities);
    }

    @AfterTest
    public void tearDown(){
        this.driver.quit();
    }
}
