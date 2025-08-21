package coms.vsb.pages.flightreservation;

import coms.vsb.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchPage extends AbstractPage {

    @FindBy(id="passengers")
    private WebElement passengersSelect;

    @FindBy(id="search-flights")
    private WebElement searchFlightButton;


    public FlightSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(passengersSelect));
        return this.passengersSelect.isDisplayed();
    }

    public void selectPassengers(String noOfPassengers){
        Select passengers = new Select(passengersSelect);
        passengers.selectByValue(noOfPassengers);
    }

    public void goToFlightSelection(){
        this.searchFlightButton.click();
    }

}
