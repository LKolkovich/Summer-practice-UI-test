package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import pages.HomePage;
import pages.LoginPage;
import pages.MainPage;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

/**
 * Абстрактный базовый класс для представления тестов.
 * Этот класс предоставляет методы для работы с браузером.
 */

abstract public class BaseTest {
    private final static String PROPERTIES_PATH = "config.properties";
    private final static String BASE_URL_PROPERTY = "web.url";
    private final static String BROWSER_PROPERTY = "web.browser";
    private final static String RESOLUTION_PROPERTY = "web.resolution";
    private final static String USER_EMAIL_PROPERTY = "user.email";
    private final static String USER_PASSWORD_PROPERTY = "user.password";
    private final static String CONFIG_TIMEOUT_PROPERTY = "config.timeout";
    private static final String CONFIG_PAGE_TIMEOUT_PROPERTY = "config.pageLoadTimeout";
    private static final String CONFIG_PAGE_LOAD_STRATEGY_PROPERTY = "config.pageLoadStrategy";
    private static final String CONFIG_HEADLESS_PROPERTY = "config.headless";
    private final static Properties properties;
    protected final Logger logger = LogManager.getLogger(this.getClass());


    /**
     * Блок статическиой инициализации файла с зависимостями
     */
    static {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(PROPERTIES_PATH)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Метод для настройки окружения перед каждым тестом.
     * Настраивает браузер, его размеры и параметры, а также открывает базовый URL.
     */
    @BeforeEach
    public void setUp(){
        Configuration.browser = properties.getProperty(BROWSER_PROPERTY);
        Configuration.browserSize = properties.getProperty(RESOLUTION_PROPERTY);
        Configuration.headless = Boolean.parseBoolean(properties.getProperty(CONFIG_HEADLESS_PROPERTY));
        Configuration.timeout = Long.parseLong(properties.getProperty(CONFIG_TIMEOUT_PROPERTY));
        Configuration.pageLoadTimeout = Long.parseLong(properties.getProperty(CONFIG_PAGE_TIMEOUT_PROPERTY));
        Configuration.pageLoadStrategy = properties.getProperty(CONFIG_PAGE_LOAD_STRATEGY_PROPERTY);
        Selenide.open(properties.getProperty(BASE_URL_PROPERTY));
    }


    /**
     * Метод для завершения тестов и очистки окружения после каждого теста.
     * Включает паузу на 5 секунд и закрытие браузера.
     */
    @AfterEach
    public void tearDown() {
        logger.info("tear down");
        closeWebDriver();
    }


    /**
     * Метод для авторизации на сайте.
     * Переходит на страницу логина, вводит учетные данные и входит на сайт.
     * @return HomePage - возвращает объект домашней страницы после успешного входа
     */
    public HomePage auth() {
        MainPage mainPage = new MainPage();
        logger.info("main page is opened");
        LoginPage loginPage = mainPage.clickLogin();
        logger.info("login page is opened");
        return loginPage.login(properties.getProperty(USER_EMAIL_PROPERTY), properties.getProperty(USER_PASSWORD_PROPERTY));
    }
}
