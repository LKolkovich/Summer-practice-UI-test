package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ex.ElementNotFound;
import utils.Exceptions.ElementNotVisibleException;

/**
 * Класс Input представляет текстовое поле на веб-странице и предоставляет методы для взаимодействия с ним.
 * Наследует функциональность из класса BaseElement.
 */
public class Input extends BaseElement {
    protected static final String INPUT_TAG = "input";

    /**
     * Защищенный конструктор Input с указанным XPath.
     *
     * @param xPath XPath выражение для нахождения элемента.
     */
    protected Input(String xPath) {
        super(xPath);
    }

    /**
     * Приватный конструктор Input с указанным XPath и параметром.
     *
     * @param xPath XPath выражение для нахождения элемента.
     * @param param Параметр, используемый вместе с XPath выражением.
     */
    private Input(String xPath, String param) {
        super(xPath, param, INPUT_TAG);
    }

    /**
     * Приватный конструктор Input с указанным XPath, параметром и индексом.
     *
     * @param xPath XPath выражение для нахождения элемента.
     * @param param Параметр, используемый вместе с XPath выражением.
     * @param index Индекс элемента.
     */
    private Input(String xPath, String param, int index) {
        super(xPath, param, INPUT_TAG, index);
    }

    /**
     * Защищенный конструктор Input с указанным XPath, параметром и HTML тегом.
     *
     * @param xPath XPath выражение для нахождения элемента.
     * @param param Параметр, используемый вместе с XPath выражением.
     * @param htmlName HTML тег элемента.
     * @param noHtml Указывает, используется ли HTML тег.
     */
    protected Input(String xPath, String param, String htmlName, boolean noHtml) {
        super(xPath, param, htmlName);
    }

    /**
     * Ждёт пока элемент появится на странице, а затем заполняет его переданным текстом
     * @param value - текст для заполнения
     */
    public void fill(String value) {
        try {
            baseElement.shouldBe(Condition.visible).setValue(value);
        } catch (ElementNotFound e) {
            throw new ElementNotVisibleException("Элемент не появился на странице в течение заданного времени: " + baseElement, e);
        }
    }

    /**
     * Создает экземпляр Input на основе атрибута id.
     *
     * @param id значение атрибута id
     * @return экземпляр Input
     */
    public static Input byId(String id) {
        return new Input(ID_XPATH, id);
    }

    /**
     * Создает экземпляр Input на основе имени класса.
     *
     * @param className имя класса
     * @return экземпляр Input
     */
    public static Input byClass(String className) {
        return new Input(CLASS_XPATH, className);
    }

    /**
     * Создает экземпляр Input на основе атрибута data и индекса.
     *
     * @param data значение атрибута data
     * @param index индекс элемента
     * @return экземпляр Input
     */
    public static Input byDataAndIndex(String data, int index) {
        return new Input(DATA_XPATH, data, index);
    }

    /**
     * Создает экземпляр Input на основе значения атрибута placeholder.
     *
     * @param placeholder значение атрибута placeholder
     * @return экземпляр Input
     */
    public static Input byPlaceholder(String placeholder) {
        return new Input(PLACEHOLDER_XPATH, placeholder);
    }

}