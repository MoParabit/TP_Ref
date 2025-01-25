package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Configuration;
import utils.IConstant;

import static common.CommonActions.*;
import static utils.IConstant.*;

import java.time.Duration;

public class RmaAccounting {

	WebDriver driver;
	WebDriverWait wait;
	Configuration configuration;

	public RmaAccounting(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		configuration = new Configuration();
	}

	@FindBy(id = "Email")
	WebElement usernameField;
	@FindBy(id = "Password")
	WebElement passwordField;
	@FindBy(xpath = "//button[text()='SIGN IN ']")
	WebElement signInBtn;
	@FindBy(xpath = "(//span[text()='RMA Accounting'])[1]")
	WebElement rmaAccountingBtn;
	@FindBy(xpath = "(//span[text()='Cases'])[1]")
	WebElement casesBtn;
	@FindBy(xpath = "(//span[@class='k-icon k-i-filter'])[6]")
	WebElement filterIconForStatus;
	@FindBy(xpath = "(//input[@title='Value'])[1]")
	WebElement filterTextFieldForStatus;
	@FindBy(xpath = "//td[@data-field='CurrentStatus']")
	WebElement currentStatus;
	@FindBy(xpath = "//button[text()='Filter']")
	WebElement filterBtnForStatus;
	@FindBy(xpath = "//td[@data-field='SerialNumber']")
	WebElement serialNumberField;
	@FindBy(xpath = "//a[@class='text-primary']")
	WebElement caseNumberField;
	@FindBy(xpath = "//td[@data-field='CarrierTrackingNumber']")
	WebElement trackingNumberField;
	@FindBy(xpath = "//td[@data-field='SerialNumber']")
	WebElement serialNumberTextField;
	@FindBy(xpath = "(//span[@role='button'])[6]")
	WebElement productUnderWarrantyDropdownBtn;
	@FindBy(xpath = "//div[@id='submitBtnLbl']")
	WebElement submitBtnForCaseForm;

	public void login() {
		inputText(usernameField, configuration.getProperties(USERNAME));
		inputText(passwordField, configuration.getProperties(PASSWORD));
		clickElement(signInBtn);
		clickElement(rmaAccountingBtn);
		clickElement(casesBtn);
	}

	public void endOfLife() {
		clickElement(rmaAccountingBtn);
		clickElement(casesBtn);
		clickElement(filterIconForStatus);
		inputText(filterTextFieldForStatus, "End of Life");
		clickElement(filterBtnForStatus);
		verifyTextOfTheWebElement(currentStatus, "End of Life");
		String serialNumber = serialNumberField.getText();
		clickElement(caseNumberField);
		String trackingNumber = trackingNumberField.getText();
		verifyTextOfTheWebElement(trackingNumberField, trackingNumber);
		scrollIntoViewTheElementUsingJavascriptExecutor(driver, serialNumberTextField);
		verifyTextOfTheWebElement(serialNumberTextField, serialNumber);
		clickElement(productUnderWarrantyDropdownBtn);
		inputText(productUnderWarrantyDropdownBtn, "No");
		// clickElement(submitBtnForCaseForm);
	}

	public void evaluated() {
		clickElement(rmaAccountingBtn);
		clickElement(casesBtn);
		clickElement(filterIconForStatus);
		inputText(filterTextFieldForStatus, "Evaluated");
		clickElement(filterBtnForStatus);
		verifyTextOfTheWebElement(currentStatus, "Evaluated");
		String serialNumber = serialNumberField.getText();
		clickElement(caseNumberField);
		String trackingNumber = trackingNumberField.getText();
		verifyTextOfTheWebElement(trackingNumberField, trackingNumber);
		scrollIntoViewTheElementUsingJavascriptExecutor(driver, serialNumberTextField);
		verifyTextOfTheWebElement(serialNumberTextField, serialNumber);
		clickElement(productUnderWarrantyDropdownBtn);
		inputText(productUnderWarrantyDropdownBtn, "Yes");
		// clickElement(submitBtnForCaseForm);
	}

	public void rejected() {
		clickElement(rmaAccountingBtn);
		clickElement(casesBtn);
		clickElement(filterIconForStatus);
		inputText(filterTextFieldForStatus, "Rejected");
		clickElement(filterBtnForStatus);
		verifyTextOfTheWebElement(currentStatus, "Rejected");
		String serialNumber = serialNumberField.getText();
		clickElement(caseNumberField);
		pause(3000);
		Alert alert = driver.switchTo().alert();
		System.out.println("\nAlert Text: " + alert.getText());
		alert.accept();
		String trackingNumber = trackingNumberField.getText();
		verifyTextOfTheWebElement(trackingNumberField, trackingNumber);
		scrollIntoViewTheElementUsingJavascriptExecutor(driver, serialNumberTextField);
		verifyTextOfTheWebElement(serialNumberTextField, serialNumber);
		clickElement(productUnderWarrantyDropdownBtn);
		inputText(productUnderWarrantyDropdownBtn, "No");
		// clickElement(submitBtnForCaseForm);
	}

	public void finalTest(String status) {

		RmaAccounting ra = new RmaAccounting(driver);


		switch (status) {
			case "End Of Life":
				if ("End Of Life".equals(currentStatus.getText())){
				ra.endOfLife();
				}
				break;

			case "Evaluated":
				if ("Evaluated".equals(currentStatus.getText())){
					ra.evaluated();
				}
				break;

			case "Rejected":
				if ("Rejected".equals(currentStatus.getText())){
					ra.rejected();
				}
				break;

			default:
				throw new RuntimeException("Status Not Valid. Use only End Of Life, Evaluated or Rejected");
		}
	}
}