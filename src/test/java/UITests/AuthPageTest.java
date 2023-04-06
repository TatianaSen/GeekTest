package UITests;

import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthPageTest extends BaseTest {

    @Test
    @DisplayName("Авторизация зарегистрированного пользователя")
    @Description("У пользователя валидные username и password")
    void validAuthTest() throws InterruptedException{
        authPage.auth("senichkina","88cb3e3b69");
        Thread.sleep(2000);
        assertEquals("https://test-stand.gb.ru/", driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Авторизация несуществуюшего пользователя")
    @Description("Username и password отвечают требованиям")
    void noUserTest() throws InterruptedException{
        authPage.auth("ivanov","79hjj87yh");
        assertEquals("https://test-stand.gb.ru/login", driver.getCurrentUrl());
        Thread.sleep(5000);
        assertEquals("Invalid credentials.", authPage.messageError().getText());
        assertEquals("401", authPage.codeError().getText());
    }
    @Test
    @DisplayName("Авторизация пользователя c валидными данными")
    @Description("Username c граничным значением в 3 символа и валидный password")
    void minUsernameAuthTest() throws InterruptedException {
        authPage.auth("www", "4eae35f1b3");
        Thread.sleep(2000);
        assertEquals("https://test-stand.gb.ru/", driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Авторизация пользователя c валидными данными")
    @Description("Username c граничным значением в 20 символа и валидный password")
    void maxUsernameAuthTest() throws InterruptedException {
        authPage.auth("qqqqqqqqqqqqqqqqqqqq", "b49200001e");
        Thread.sleep(2000);
        assertEquals("https://test-stand.gb.ru/", driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Авторизация c невалидными данными")
    @Description("Username в 2 символа (ниже минимальной границы в 3 символа) и валидный password")
    void LessThanMinUsernameAuthTest() throws InterruptedException {
        authPage.auth("qq", "099b3b0601");
        Thread.sleep(5000);
        assertEquals("https://test-stand.gb.ru/login", driver.getCurrentUrl());
        assertEquals("Invalid credentials.", authPage.messageError().getText());
        assertEquals("401", authPage.codeError().getText());
    }
    @Test
    @DisplayName("Авторизация c невалидными данными")
    @Description("Username в 21 символ (выше максимальной границы в 20 символов) и валидный password")
    void MoreThanMinUsernameAuthTest() throws InterruptedException {
        authPage.auth("wwwwwwwwwwwwwwwwwwwww", "47d1eec78c");
        Thread.sleep(5000);
        assertEquals("https://test-stand.gb.ru/login", driver.getCurrentUrl());
        assertEquals("Invalid credentials.", authPage.messageError().getText());
        assertEquals("401", authPage.codeError().getText());
    }
    @Test
    @DisplayName("Авторизация c невалидными данными")
    @Description("Username и password пустые")
    void EmptyFieldsAuthTest() throws InterruptedException {
        authPage.auth("", "");
        assertEquals("https://test-stand.gb.ru/login", driver.getCurrentUrl());
        Thread.sleep(5000);
        assertEquals("Invalid credentials.", authPage.messageError().getText());
        assertEquals("401", authPage.codeError().getText());
    }
    @Test
    @DisplayName("Авторизация c невалидными данными")
    @Description("Username содержит русские буквы, валидный password")
    void NoLatinAuthTest() throws InterruptedException {
        authPage.auth("иииии", "6b2e0c76d0");
        Thread.sleep(5000);
        assertEquals("https://test-stand.gb.ru/login", driver.getCurrentUrl());
        assertEquals("Invalid credentials.", authPage.messageError().getText());
        assertEquals("401", authPage.codeError().getText());
    }
}
