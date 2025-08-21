package coms.vsb.pages.vendorportal;

import coms.vsb.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashBoardPage extends AbstractPage {
     private static Logger logger = LoggerFactory.getLogger(DashBoardPage.class);
    @FindBy(id="monthly-earning")
    private WebElement monthlyEarningElement;

    @FindBy(id="annual-earning")
    private WebElement annualEarningElement;

    @FindBy(id="profit-margin")
    private WebElement profitMarginElement;

    @FindBy(id="available-inventory")
    private WebElement availableInventoryElement;

    @FindBy(css="#dataTable_filter input")
    private WebElement searchInput;

    @FindBy(id="dataTable_info")
    private WebElement searchResultsCountElement;

    @FindBy(css="img.img-profile")
    private WebElement profilePictureElement;

    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    @FindBy(css="#logoutModal a")
    private WebElement modalLogoutButton;

    public DashBoardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(monthlyEarningElement));
        return this.monthlyEarningElement.isDisplayed();
    }

    public String getMonthlyEarning(){
        return this.monthlyEarningElement.getText();
    }
    public String getAnnualEarning(){
        return this.annualEarningElement.getText();
    }
    public String getProfitMargin(){
        return this.profitMarginElement.getText();
    }
    public String getAvailableInventory(){
        return this.availableInventoryElement.getText();
    }

    public void searchOrderHistoryBy(String keyword){
        this.searchInput.sendKeys(keyword);
    }

    public void logout(){
        this.profilePictureElement.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutLink));
        this.logoutLink.click();
        this.wait.until(ExpectedConditions.visibilityOf(modalLogoutButton));
        this.modalLogoutButton.click();

    }

    public int getSearchResultCount(){
        String [] searchResultArray = this.searchResultsCountElement.getText().split(" ");
        int count = Integer.parseInt(searchResultArray[5]);
        logger.info("Results count {}",count);
        return count;

    }
}
