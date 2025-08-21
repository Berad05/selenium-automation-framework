package coms.vsb.tests.vendorportal;

import coms.vsb.pages.vendorportal.DashBoardPage;
import coms.vsb.pages.vendorportal.LoginPage;
import coms.vsb.tests.AbstractTest;
import coms.vsb.tests.vendorportal.model.VendorPortalTestData;
import coms.vsb.util.Config;
import coms.vsb.util.Constants;
import coms.vsb.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest extends AbstractTest {

    private LoginPage loginPage;
    private DashBoardPage dashBoardPage;
    private VendorPortalTestData testData;
    @BeforeTest
    @Parameters("testDataPath")
    public void setVendorPortalPages(String testDataPath){
        this.loginPage = new LoginPage(driver);
        this.dashBoardPage = new DashBoardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);

    }

    @Test
    public void loginTest(){

        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(), testData.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashBoardTest(){
        Assert.assertTrue(dashBoardPage.isAt());
        Assert.assertEquals(dashBoardPage.getMonthlyEarning(),testData.monthlyEarning());
        Assert.assertEquals(dashBoardPage.getAnnualEarning(),testData.annualEarning());
        Assert.assertEquals(dashBoardPage.getProfitMargin(),testData.profitMargin());
        Assert.assertEquals(dashBoardPage.getAvailableInventory(),testData.availableInventory());

        dashBoardPage.searchOrderHistoryBy(testData.searchKeyword());
        Assert.assertEquals(dashBoardPage.getSearchResultCount(),testData.searchResultsCount());
    }

    @Test(dependsOnMethods = "dashBoardTest")
    public void logoutTest(){
        dashBoardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }
}
