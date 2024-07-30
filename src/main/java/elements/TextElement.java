package elements;


/**
 * Класс TextElement является специализированной версией класса Input,
 * предназначенной для взаимодействия с элементами не являющимися input на веб-странице
 */
public class TextElement extends Input {
    protected static final String TEXTAREA_TAG = "textarea";

    /**
     * Приватный конструктор TextElement с указанным XPath, параметром и типом элемента.
     *
     * @param xPath XPath выражение для нахождения элемента.
     * @param param Параметр, используемый вместе с XPath выражением.
     * @param elementType Тип HTML элемента (например, div, span, textarea).
     */
    private TextElement(String xPath, String param, String elementType) {
        super(xPath, param, elementType, true);
    }

    /**
     * Приватный конструктор TextElement с указанным XPath.
     *
     * @param xPath XPath выражение для нахождения элемента.
     */
    private TextElement(String xPath) {
        super(xPath);
    }

    /**
     * Создает экземпляр TextElement на основе имени класса элемента div.
     *
     * @param className Имя класса.
     * @return Экземпляр TextElement.
     */
    public static TextElement byDivClass(String className) {
        return new TextElement(CLASS_XPATH, className, DIV_TAG);
    }

    /**
     * Создает экземпляр TextElement на основе атрибута data-test элемента div.
     *
     * @param dataTest Значение атрибута data-test.
     * @return Экземпляр TextElement.
     */
    public static TextElement byDivDataTest(String dataTest) {
        return new TextElement(DATA_XPATH, dataTest, DIV_TAG);
    }

    /**
     * Создает экземпляр TextElement на основе атрибута data-pendo элемента div.
     *
     * @param dataPendo Значение атрибута data-pendo.
     * @return Экземпляр TextElement.
     */
    public static TextElement byDivDataPendo(String dataPendo){
        return new TextElement(DATA_PENDO_XPATH, dataPendo, DIV_TAG);
    }

    /**
     * Создает экземпляр TextElement на основе атрибута data-test элемента span.
     *
     * @param dataTest Значение атрибута data-test.
     * @return Экземпляр TextElement.
     */
    public static TextElement bySpanDataTest(String dataTest){
        return new TextElement(DATA_XPATH, dataTest, SPAN_TAG);
    }

    /**
     * Создает экземпляр TextElement на основе имени класса элемента span.
     *
     * @param className Имя класса.
     * @return Экземпляр TextElement.
     */
    public static TextElement bySpanClass(String className){
        return new TextElement(CLASS_XPATH, className, SPAN_TAG);
    }

    /**
     * Создает экземпляр TextElement на основе атрибута data-test элемента textarea.
     *
     * @param dataTest Значение атрибута data-test.
     * @return Экземпляр TextElement.
     */
    public static TextElement byTextAreaData(String dataTest){
        return new TextElement(DATA_XPATH, dataTest, TEXTAREA_TAG);
    }

    /**
     * Создает экземпляр TextElement на основе части текста, содержащегося в элементе span.
     *
     * @param text - часть текста для поиска.
     * @return Экземпляр TextElement.
     */
    public static TextElement byTextPart(String text) {
        return new TextElement(TEXT_CONTAINS_XPATH, text, SPAN_TAG);
    }

    /**
     * Создает экземпляр TextElement на основе полного XPath выражения.
     *
     * @param xPath Полное XPath выражение для нахождения элемента.
     * @return Экземпляр TextElement.
     */
    public static TextElement byFullXPath(String xPath) {
        return new TextElement(xPath);
    }
}
