package elements.Buttons;

/**
 * Класс DivButton является специализированной версией класса Button,
 * предназначенной для взаимодействия с элементами div на веб-странице,
 * которые ведут себя как кнопки.
 */
public class DivButton extends Button {
    private static final String CLASS_AND_TEXT_CONTAINS_XPATH = "//div[@class='%s'][contains(text(), '%s')]";

    /**
     * Конструктор DivButton с указанным XPath и параметром.
     *
     * @param xPath XPath выражение для нахождения элемента div.
     * @param param Параметр, используемый вместе с XPath выражением.
     */
    private DivButton(String xPath, String param) {
        super(xPath, param, DIV_TAG);
    }

    /**
     * Конструктор DivButton с указанным XPath.
     *
     * @param xPath XPath выражение для нахождения элемента div.
     */
    private DivButton(String xPath) {
        super(xPath);
    }

    /**
     * Создает экземпляр DivButton на основе атрибута data-test.
     *
     * @param dataTest Значение атрибута data-test.
     * @return Экземпляр DivButton.
     */
    public static DivButton byDataTest(String dataTest) {
        return new DivButton(DATA_XPATH, dataTest);
    }

    /**
     * Создает экземпляр DivButton на основе имени класса.
     *
     * @param className Имя класса.
     * @return Экземпляр DivButton.
     */
    public static DivButton byClass(String className) {
        return new DivButton(CLASS_XPATH, className);
    }

    /**
     * Создает экземпляр DivButton на основе имени класса и содержащегося текста.
     *
     * @param className Имя класса.
     * @param text Текст, который должен содержаться в элементе div.
     * @return Экземпляр DivButton.
     */
    public static DivButton byClassAndTextContains(String className, String text) {
        return new DivButton(String.format(CLASS_AND_TEXT_CONTAINS_XPATH, className, text));
    }
}
