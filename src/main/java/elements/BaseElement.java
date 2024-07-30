package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;
import com.codeborne.selenide.ex.ElementNotFound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.lang.reflect.UndeclaredThrowableException;

import static com.codeborne.selenide.Selenide.*;

/**
 * Абстрактный базовый класс для представления веб-элементов.
 * Этот класс предоставляет методы для работы с веб-элементами с использованием Selenide.
 */
abstract public class BaseElement {
    /**
     * Веб-элемент, логику взаимодействия с которым реализует класс
     */
    protected final SelenideElement baseElement;

    /**
     * Общие XPath шаблоны для различных типов HTML элементов
     */
    protected static final String ARIA_PRESSED_AND_DATA_TEST_CONTAINS_XPATH = "//%s[@aria-pressed='true']" +
            "[contains(@data-test, '%s')]";
    protected static final String ID_XPATH = "//%s[@id='%s']";
    protected static final String CLASS_XPATH = "//%s[@class='%s']";
    protected static final String TEXT_CONTAINS_XPATH = "//%s[(text()='%s')]";
    protected static final String DATA_XPATH = "//%s[@data-test='%s']";
    protected static final String PLACEHOLDER_XPATH = "//%s[@placeholder='%s']";
    protected static final String DATA_PENDO_XPATH = "//%s[@data-pendo='%s']";

    /**
     * HTML-теги элементов
     */
    protected static final String DIV_TAG = "div";
    protected static final String SPAN_TAG = "span";
    protected static final String OPEN_BRACKET = "[";
    protected static final String CLOSE_BRACKET = "]";

    protected final String xPath;

    public static final Logger logger = LogManager.getLogger();

    /**
     * Конструктор с полным XPath выражением.
     *
     * @param fullXpath Полное XPath выражение для нахождения элемента.
     */
    protected BaseElement(String fullXpath) {
        this.baseElement = $x(fullXpath);
        this.xPath = fullXpath;
    }

    /**
     * Конструктор с XPath выражением и значением атрибута.
     *
     * @param xpath XPath выражение для нахождения элемента.
     * @param attributeValue Значение атрибута для XPath выражения.
     * @param elementType Тип HTML элемента.
     */
    protected BaseElement(String xpath, String attributeValue, String elementType) {
        this.xPath = String.format(xpath, elementType, attributeValue);
        this.baseElement = $x(this.xPath);
    }

    /**
     * Конструктор с XPath выражением, значением атрибута и типом элемента.
     *
     * @param xpath XPath выражение для нахождения элемента.
     * @param elementType Тип HTML элемента.
     * @param attributeValue1 Первое значение атрибута для XPath выражения.
     * @param attributeValue2 Второе значение атрибута для XPath выражения.
     */
    protected BaseElement(String xpath, String elementType, String attributeValue1, String attributeValue2) {
        this.xPath = String.format(xpath, elementType, attributeValue1, attributeValue2);
        this.baseElement = $x(this.xPath);
    }

    /**
     * Конструктор с XPath выражением, значением атрибута, типом элемента и индексом.
     *
     * @param xpath XPath выражение для нахождения элемента.
     * @param attributeValue Значение атрибута для XPath выражения.
     * @param elementType Тип HTML элемента.
     * @param index Индекс элемента в списке.
     */
    protected BaseElement(String xpath, String attributeValue, String elementType, int index) {
        this.xPath = String.format(xpath, elementType, attributeValue) + OPEN_BRACKET + index + CLOSE_BRACKET;
        var elementsList = $$x(String.format(xpath, elementType, attributeValue));
        this.baseElement = elementsList.get(index);
    }

    /**
     * Метод для получения атрибута веб-элемента по его названию
     *
     * @param attributeName - название атрибута
     * @return - значение атрибуда
     */
    public String getAttributeValue(String attributeName) {
        try {
            return baseElement.getAttribute(attributeName);
        } catch (UndeclaredThrowableException | ElementNotFound e) {
            logger.error("element by xPath " + xPath + " not found");
            return "";
        }
    }

    /**
     * Проверка отображается ли элемент на странице. Ждёт в течение времени установленного в конфиге
     * Если время истекло и элемент не виден, логирует это и возвращает false
     *
     * @return - true, если элемент отображается, иначе false
     */
    public boolean isDisplayed() {
        try {
            return baseElement.shouldBe(Condition.visible)
                    .isDisplayed();
        } catch (UndeclaredThrowableException | ElementNotFound e) {
            logger.warn("element by xPath " + xPath + " not found");
            return false;
        }
    }

    /**
     * Проверка выполняет ли веб-элемент определенное условие
     *
     * @param condition - условие, для которого выполняется проверка
     * @return - true, если элемент выполняет условие, иначе false
     */
    public boolean has(WebElementCondition condition){
        return baseElement.has(condition);
    }

    /**
     * Провекрка существует ли элемент на странице
     *
     * @return - true, если существует, иначе false
     */
    public boolean isExists() {
        return baseElement.exists();
    }

    /**
     * Метод для получения текст из веб-элемента
     *
     * @return - текст веб-элемента
     */
    public String getText() {
        return baseElement.getText();
    }

    /**
     * Проверка и ожидание состояния видимости элемента
     */
    public void shouldBeVisible() {
        baseElement.shouldBe(Condition.visible);
    }

    /**
     * Проверка и ожидание состояния невидимости элемента
     */
    public void shouldDisappear() {
        baseElement.should(Condition.disappear);
    }

    /**
     * Проверка и ожидание существования элемента
     */
    public void shouldExist() {
        baseElement.should(Condition.exist);
    }
}
