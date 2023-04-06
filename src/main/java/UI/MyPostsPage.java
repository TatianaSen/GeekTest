package UI;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyPostsPage extends BaseView{
    public MyPostsPage (WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//span[text()='Home']")
    private WebElement homeButton;

    @FindBy(xpath = "//h1[text()='Blog']")
    private WebElement titleBlog;

    @FindBy(xpath = "//button[@id='create-btn']")
    private WebElement createPostButton;

    @FindBy(xpath = "//i[contains(text(), 'sort')][2]/parent::button")
    private WebElement orderButton;

    @FindBy(xpath = "//button[contains(@class, 'switch')]")
    private WebElement notMyPostSwitcher;

    @FindBy(xpath = "//a[contains(text(), 'Previous Page')]")
    private WebElement previousPageButton;

    @FindBy(xpath = "//a[contains(text(), 'Next Page')]")
    private WebElement nextPageButton;

    @FindBy(xpath = "//a[contains(@class, 'post')][1]")
    private WebElement post;
    @FindBy(xpath = "//a[contains(@class, 'post')][1]/img")
    private WebElement postImg;
    @FindBy(xpath = "//a[contains(@class, 'post')][1]/h2")
    private WebElement postTitle;
    @FindBy(xpath = "//a[contains(@class, 'post')][1]/div")
    private WebElement postDescription;
    @FindBy(xpath = "//p[text()='No items for your filter']")
    private WebElement noPostsMessage;

    @Step("Переключение на ленту с чужими постами")
    public MyPostsPage switchToNotMyPostsPage() {
        notMyPostSwitcher.click();
        return this;
    }
    @Step("Вернуться на главную страницу")
    public MyPostsPage clickHomeButton(){
        homeButton.click();
        return this;
    }
    @Step("Сортировка постов")
    public MyPostsPage clickToOrderButton(){
        orderButton.click();
        return this;
    }
    @Step("Перейти на предыдущую страницу с постами")
    public MyPostsPage goToPreviousPage(){
        previousPageButton.click();
        return this;
    }
    @Step("Перейти на следующую страницу")
    public MyPostsPage goToNextPage(){
        nextPageButton.click();
        return this;
    }

    public WebElement getNoPostsMessage(){
        return noPostsMessage;
    }
    public int getAnyPostOnPage(){
        List<WebElement> list=driver.findElements(By.xpath("//a[contains(@class, 'post svelte-127jg4t')]"));
        return list.size();
    }
    public WebElement getPost() {
        return post;
    }
    public WebElement getPostTitle() {
        return postTitle;
    }
    public WebElement getPostDescription() {
        return postDescription;
    }
    public WebElement getPostImg() {
        return postImg;
    }


}
