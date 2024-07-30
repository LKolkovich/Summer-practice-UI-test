package pages;

import elements.Buttons.LinkButton;

/**
 * Главная страница приложения.
 * Содержит методы для взаимодействия с элементами на главной странице.
 */

public class MainPage extends BasePage{
    private final LinkButton loginButton = LinkButton.byTextPart("Log in");

    /**
     * Нажатие кнопки Log in на главной странице сайта
     *
     * @return экземпляр класса для взаимодействия с страницей логина
     */
    public LoginPage clickLogin() {
        loginButton.click();
        LoginPage loginPage = LoginPage.page(LoginPage.class);
        return loginPage;
    }
}
