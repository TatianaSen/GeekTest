package UI;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthPage extends BaseView {

    public AuthPage (WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@type='text']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@form='login']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@class='error-block svelte-uwkxn9']/h2")
    private WebElement codeError;
    @FindBy(xpath = "//div[@class='error-block svelte-uwkxn9']/p[1]")
    private WebElement messageError;
    @FindBy(xpath = "//li[contains(@class, 'surface')]")
    private WebElement helloButton;


    @Step("Авторизация")
    public MyPostsPage auth (String username, String password){
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();;
       return new MyPostsPage(driver);
    }

    public WebElement codeError() {
        return codeError;
    }
    public WebElement messageError() {
        return messageError;
    }


}

