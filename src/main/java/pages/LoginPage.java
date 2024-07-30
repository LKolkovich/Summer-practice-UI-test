package pages;

import elements.Buttons.Button;
import elements.Input;

/**
 * Страница "Вход в систему".
 * Содержит методы для ввода данных в поля электронной почты и пароля, а также для нажатия кнопки входа.
 */

public class LoginPage extends BasePage{
    private final Input emailInput = Input.byId("login-email-input");
    private final Input passwordInput = Input.byId("login-password-input");
    private final Button logInButton = Button.byClass("login-page-new__main-form-button");

    /**
     * Вводит email и пароль на странице логина, а затем нажимает кнопку Log In
     *
     * @param email - электронная почта пользователя
     * @param password - пароль пользователя
     * @return - экземпляр класса для работы с домашней страницей
     */
    public HomePage login(String email, String password) {
        emailInput.fill(email);
        logger.info("email field is filled: " + email);
        passwordInput.fill(password);
        logger.info("password field is filled: " + password);
        logInButton.click();
        logger.info("submit login button is clicked");
        return HomePage.page(HomePage.class);
    }
}