package elements;

import com.codeborne.selenide.SelenideElement;

/**
 * Предствляет логику работы с элементом, который используется для наваедения на него мыши
 * с целью отобразить скрытый элемент страницы
 */
public class MouseOverElement extends BaseElement {

    /**
     * Приватный конструктор Input с указанным XPath, параметром и HTML тегом.
     *
     * @param xPath XPath выражение для нахождения элемента.
     * @param type Тип веб-элемента.
     * @param param Параметр, используемый вместе с XPath выражением.
     */
    private MouseOverElement(String xPath, String type, String param) {
        super(xPath, param, type);
    }

    /**
     * Создает экземпляр MouseOverElement на основе значения атрибута class для элемента type
     *
     * @param className значение атрибута class
     * @param type тип веб-элемента
     * @return экземпляр MouseOverElement
     */
    public static MouseOverElement byClass(String type, String className) {
        return new MouseOverElement(CLASS_XPATH, type, className);
    }

    /**
     * Создает экземпляр MouseOverElement на основе значения атрибута data-test для элемента type
     *
     * @param dataTest значение атрибута data-test
     * @param type тип веб-элемента
     * @return экземпляр MouseOverElement
     */
    public static MouseOverElement byDataTest(String type, String dataTest) {
        return new MouseOverElement(DATA_XPATH, type, dataTest);
    }

    /**
     * Геттер для baseElement
     *
     * @return baseElement, хранящийся в классе
     */
    public SelenideElement getBaseElement() {
        return baseElement;
    }
}
