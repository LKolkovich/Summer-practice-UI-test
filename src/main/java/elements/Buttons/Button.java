package elements.Buttons;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ex.ElementNotFound;
import elements.BaseElement;
import org.openqa.selenium.JavascriptException;
import utils.Exceptions.ElementNotVisibleException;
import elements.MouseOverElement;

import static com.codeborne.selenide.Selenide.actions;

/**
 * Класс Button представляет кнопку на веб-странице и предоставляет методы для взаимодействия с ней.
 * Наследует функциональность из класса BaseElement.
 */
public class Button extends BaseElement {
    protected static final String BUTTON_TAG = "button";

    /**
     * Приватный конструктор Button с указанным XPath и параметром.
     *
     * @param xPath XPath выражение для нахождения элемента.
     * @param param Параметр, используемый вместе с XPath выражением.
     */
    private Button(String xPath, String param) {
        super(xPath, param, BUTTON_TAG);
    }

    /**
     * Защищенный конструктор Button с указанным XPath, параметром и HTML тегом.
     *
     * @param xPath XPath выражение для нахождения элемента.
     * @param param Параметр, используемый вместе с XPath выражением.
     * @param htmlName HTML тег элемента.
     */
    protected Button(String xPath, String param, String htmlName) {
        super(xPath, param, htmlName);
    }

    /**
     * Защищенный конструктор Button с указанным XPath.
     *
     * @param xPath XPath выражение для нахождения элемента.
     */
    protected Button(String xPath) {
        super(xPath);
    }

    /**
     * Защищенный конструктор Button с указанным XPath, параметром и индексом.
     *
     * @param xPath XPath выражение для нахождения элемента.
     * @param param Параметр, используемый вместе с XPath выражением.
     * @param index Индекс элемента.
     */
    protected Button(String xPath, String param, int index) {
        super(xPath, param, BUTTON_TAG, index);
    }


    /**
     * Ждет пока элемент появится на стринце и нажимает его
     */
    public void click() {
        try {
            baseElement.shouldBe(Condition.visible).click();
        } catch (ElementNotFound e) {
            throw new ElementNotVisibleException("Элемент не появился на странице в течение заданного времени: "
                    + baseElement, e);
        }
    }

    /**
     * используется для нажатия кнопок, которые появляются только при наведении курсора на них
     */
    public void clickWithMouseOver(MouseOverElement elementToMouseOver) {
        try {
            elementToMouseOver.shouldBeVisible();
            actions().moveToElement(elementToMouseOver.getBaseElement()).click(baseElement).perform();
        } catch (JavascriptException e) {
            throw new ElementNotVisibleException("Элемент не появился на странице в течение заданного времени: "
                    + baseElement, e);
        }
    }

    /**
     * Метод для наведения курсора на элемент
     */
    public void hover(){
        baseElement.hover();
    }

    /**
     * Создает экземпляр Button на основе атрибута id.
     *
     * @param id Значение атрибута id.
     * @return Экземпляр Button.
     */
    public static Button byId(String id) {
        return new Button(ID_XPATH, id);
    }

    /**
     * Создает экземпляр Button на основе имени класса.
     *
     * @param className Имя класса.
     * @return Экземпляр Button.
     */
    public static Button byClass(String className) {
        return new Button(CLASS_XPATH, className);
    }

    /**
     * Создает экземпляр Button на основе имени класса и индекса.
     *
     * @param className Имя класса.
     * @param index Индекс элемента.
     * @return Экземпляр Button.
     */
    public static Button byClass(String className, int index) { return new Button(CLASS_XPATH, className, index); }

    /**
     * Создает экземпляр Button на основе атрибута data.
     *
     * @param data Значение атрибута data.
     * @return Экземпляр Button.
     */
    public static Button byData(String data){
        return new Button(DATA_XPATH, data);
    }

    /**
     * Создает экземпляр Button на основе полного XPath выражения.
     *
     * @param xPath Полное XPath выражение для нахождения элемента.
     * @return Экземпляр Button.
     */
    public static Button byFullXPath(String xPath) {
        return new Button(xPath);
    }

    /**
     * Создает экземпляр Button на основе части значения атрибута aria-pressed и data-test.
     *
     * @param containsWord Часть значения, которая должна содержаться в атрибутах aria-pressed и data-test.
     * @return Экземпляр Button.
     */
    public static Button byAriaPressedAndDataTestContains(String containsWord) {
        return new Button(ARIA_PRESSED_AND_DATA_TEST_CONTAINS_XPATH, containsWord);
    }

}
