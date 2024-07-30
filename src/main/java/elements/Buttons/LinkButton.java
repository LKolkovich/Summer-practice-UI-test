package elements.Buttons;

/**
 * Класс LinkButton является специализированной версией класса Button,
 * предназначенной для взаимодействия с элементами a (ссылками) на веб-странице,
 * которые ведут себя как кнопки.
 */
public class LinkButton extends Button {
    protected static final String LINK_TAG = "a";

    /**
     * Конструктор LinkButton с указанным XPath и параметром.
     *
     * @param xPath XPath выражение для нахождения элемента a.
     * @param param Параметр, используемый вместе с XPath выражением.
     */
    private LinkButton(String xPath, String param) {
        super(xPath, param, LINK_TAG);
    }

    /**
     * Создает экземпляр LinkButton на основе атрибута data-test.
     *
     * @param dataTest Значение атрибута data-test.
     * @return Экземпляр LinkButton.
     */
    public static LinkButton byDataTest(String dataTest) {
        return new LinkButton(DATA_XPATH, dataTest);
    }

    /**
     * Создает экземпляр LinkButton на основе имени класса.
     *
     * @param className Имя класса.
     * @return Экземпляр LinkButton.
     */
    public static LinkButton byClass(String className) {
        return new LinkButton(CLASS_XPATH, className);
    }

    /**
     * Создает экземпляр LinkButton на основе части текста, содержащегося в элементе a.
     *
     * @param text Часть текста, который должен содержаться в элементе a.
     * @return Экземпляр LinkButton.
     */
    public static LinkButton byTextPart(String text) {
        return new LinkButton(TEXT_CONTAINS_XPATH, text);
    }

}
