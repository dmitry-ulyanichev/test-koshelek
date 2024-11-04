package definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SignupPage;

public class SignupStepDefs {

    SignupPage signupPage = new SignupPage();

    @Given("I open page {string}")
    public void iOpenPage(String url) {
        signupPage.open(url);
    }

    @Then("I wait for the element with css {string} to be present")
    public void iWaitForTheElementWithCssToBePresent(String cssSelector) {
        signupPage.waitForElementToBePresent(cssSelector);
    }

    @And("I wait for page to finish loading \\(element with xpath {string} is present)")
    public void iWaitForPageToFinishLoadingElementWithXpathIsVisible(String xpath) {
        signupPage.letPageLoad(xpath);
    }

    @When("I click on element with css {string}")
    public void iClickOnElementWithCss(String cssSelector) {
        signupPage.clickOnElement(cssSelector);
    }

    @Then("I should see error message {string} in {string}")
    public void iShouldSeeErrorMessageIn(String errorMessage, String cssSelector) {
        signupPage.lookForErrorMessage(errorMessage, cssSelector);
    }

    @When("I type {string} into element with css {string}")
    public void iTypeIntoElementWithCss(String input, String cssSelector) {
        signupPage.typeIntoInputField(input, cssSelector);
    }

    @And("I click away from element with css {string}")
    public void iClickAwayFromElementWithCss(String cssSelector) {
        signupPage.clickAwayFromElement(cssSelector);
    }

    @And("I should see error popup with message {string}")
    public void iShouldSeeErrorPopupWithMessage(String popupErrorMessage) throws Exception {
        signupPage.lookForPopupErrorMessage(popupErrorMessage);
    }

    @And("I should see user-agreement checkbox show an error")
    public void iShouldSeeUserAgreementCheckboxShowAnError() throws Exception {
        signupPage.assertAgreementError();
    }
}
