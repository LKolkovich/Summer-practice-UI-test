package tests;

import org.junit.jupiter.api.Test;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тестирование успешного входа в систему.
 * Проверяет, что после успешного входа на главной странице отображается кнопка профиля.
 */
public class LoginTest extends BaseTest{

    /**
     * Тестирование входа в систему
     * Пользователь должен авторизоваться в системе по своему логину паролю
     */
    @Test
    public void testSuccessfulLogin() {
        HomePage homePage = auth();
        assertTrue(homePage.checkProfileButton(), "Profile button should be visible after successful login");
    }
}
