package UITests;

import UI.MyPostsPage;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MyPostsTest extends BaseTest{
    @Test
    @DisplayName("Отображение постов после успешной авторизации")
    @Description("Проверка на одном посте")
    void viewPostsTest() throws InterruptedException{
        MyPostsPage myPostsPage = new MyPostsPage(driver);
        authPage.auth("senichkina","88cb3e3b69");
        Thread.sleep(2000);
        assertEquals("https://test-stand.gb.ru/", driver.getCurrentUrl());
        assertTrue(myPostsPage.getPost().isDisplayed());
    }
    @Test
    @DisplayName("Отсутствие постов после успешной авторизации")
    void noPostsTest() throws InterruptedException {
        MyPostsPage myPostsPage = new MyPostsPage(driver);
        authPage.auth("www", "4eae35f1b3");
        Thread.sleep(2000);
        assertEquals("https://test-stand.gb.ru/", driver.getCurrentUrl());
        assertTrue(myPostsPage.getNoPostsMessage().isDisplayed());
    }
    @Test
    @DisplayName("Отображение изображений или заглушек  у постов")
    @Description("Проверка на одном посте")
    void imgPostsTest() throws InterruptedException{
        MyPostsPage myPostsPage = new MyPostsPage(driver);
        authPage.auth("senichkina","88cb3e3b69");
        Thread.sleep(2000);
        assertEquals("https://test-stand.gb.ru/", driver.getCurrentUrl());
        assertTrue(myPostsPage.getPostImg().isDisplayed());
    }
    @Test
    @DisplayName("Отображение заголовков у постов")
    @Description("При отсутствии заголовка он просто не отображается, проверка на одном посте")
    void titlePostsTest() throws InterruptedException{
        MyPostsPage myPostsPage = new MyPostsPage(driver);
        authPage.auth("senichkina","88cb3e3b69");
        Thread.sleep(2000);
        assertEquals("https://test-stand.gb.ru/", driver.getCurrentUrl());
        assertTrue(myPostsPage.getPostTitle().isDisplayed());
    }
    @Test
    @DisplayName("Отображение описания у постов")
    @Description("При отсутствии описания он просто не отображается, проверка на одном посте")
    void descriptionPostsTest() throws InterruptedException{
        MyPostsPage myPostsPage = new MyPostsPage(driver);
        authPage.auth("senichkina","88cb3e3b69");
        Thread.sleep(2000);
        assertEquals("https://test-stand.gb.ru/", driver.getCurrentUrl());
        assertTrue(myPostsPage.getPostDescription().isDisplayed());
    }
    @Test
    @DisplayName("Кол-во постов на странице")
    @Description("Должно выводиться по 10 постов на странице")
    void postsCountTest() throws InterruptedException {
        MyPostsPage myPostsPage = new MyPostsPage(driver);
        authPage.auth("senichkina", "88cb3e3b69");
        Thread.sleep(2000);
        assertEquals("https://test-stand.gb.ru/", driver.getCurrentUrl());
        assertThat(10, equalTo(myPostsPage.getAnyPostOnPage()));
    }
    @Test
    @DisplayName("Переход на следующую страницу с постами")
    void nextPageTest() throws InterruptedException {
        MyPostsPage myPostsPage = new MyPostsPage(driver);
        authPage.auth("senichkina", "88cb3e3b69");
        Thread.sleep(2000);
        myPostsPage.goToNextPage();
        Thread.sleep(10000);
        assertEquals("https://test-stand.gb.ru/?page=2", driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Переход на предыдущую страницу с постами")
    void previousPageTest() throws InterruptedException {
        MyPostsPage myPostsPage = new MyPostsPage(driver);
        authPage.auth("senichkina", "88cb3e3b69");
        Thread.sleep(2000);
        myPostsPage.goToNextPage();
        Thread.sleep(10000);
        myPostsPage.goToPreviousPage();
        Thread.sleep(5000);
        assertEquals("https://test-stand.gb.ru/?page=1", driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Переход на главную страницу через кнопку Home")
    void homePageTest() throws InterruptedException {
        MyPostsPage myPostsPage = new MyPostsPage(driver);
        authPage.auth("senichkina", "88cb3e3b69");
        Thread.sleep(2000);
        myPostsPage.goToNextPage();
        Thread.sleep(5000);
        myPostsPage.clickHomeButton();
        Thread.sleep(5000);
        assertEquals("https://test-stand.gb.ru/", driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Сортировка постов")
    void sortPostsTest() throws InterruptedException {
        MyPostsPage myPostsPage = new MyPostsPage(driver);
        authPage.auth("senichkina", "88cb3e3b69");
        Thread.sleep(2000);
        myPostsPage.clickToOrderButton();
        Thread.sleep(5000);
        assertEquals("https://test-stand.gb.ru/?sort=createdAt&order=DESC", driver.getCurrentUrl());
        myPostsPage.clickToOrderButton();
        Thread.sleep(5000);
        assertEquals("https://test-stand.gb.ru/?sort=createdAt&order=ASC", driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Переключение на ленту с чужими постами")
    void notMyPostsPageTest() throws InterruptedException {
        MyPostsPage myPostsPage = new MyPostsPage(driver);
        authPage.auth("senichkina", "88cb3e3b69");
        Thread.sleep(2000);
        myPostsPage.switchToNotMyPostsPage();
        Thread.sleep(5000);
        assertEquals("https://test-stand.gb.ru/?owner=notMe&sort=createdAt&order=ASC", driver.getCurrentUrl());

    }

}
