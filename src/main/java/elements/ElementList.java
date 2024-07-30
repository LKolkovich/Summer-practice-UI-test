package elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import utils.Exceptions.ElementNotVisibleException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.codeborne.selenide.Selenide.$$x;


/**
 * Класс, реализующий логику работы над списком элементов, имеющих одинаковый относительных xPath
 */
public class ElementList extends BaseElement {
    private static final String CLASS_XPATH = "//%s[@class='%s']";
    private static final String SPAN_TAG = "span";
    private static final String DIV_TAG = "div";
    private final ElementsCollection collection;

    /**
     * Приватный конструктор Input с указанным XPath, параметром и HTML тегом.
     *
     * @param xPath XPath выражение для нахождения элемента.
     * @param param Параметр, используемый вместе с XPath выражением.
     * @param elementType Тип веб-элемента.
     */
    private ElementList(String xPath, String param, String elementType) {
        super(xPath, param, elementType);
        collection = $$x(this.xPath);
    }

    private ElementList(String xPath) {
        super(xPath);
        collection = $$x(this.xPath);
    }

    /**
     * Создает экземпляр ElementList на основе значения атрибута class для span.
     *
     * @param className значение атрибута class
     * @return экземпляр ElementList
     */
    public static ElementList bySpanClass(String className) {
        return new ElementList(CLASS_XPATH, className, SPAN_TAG);
    }

    /**
     * Создает экземпляр ElementList на основе его xPath
     *
     * @param xPath xPath элемента
     * @return экземпляр ElementList
     */
    public static ElementList byFullXPath(String xPath) {
        return new ElementList(xPath);
    }

    /**
     * Создает экземпляр ElementList на основе значения атрибута class для div.
     *
     * @param className значение атрибута class
     * @return экземпляр ElementList
     */
    public static ElementList byDivClass(String className) {
        return new ElementList(CLASS_XPATH, className, DIV_TAG);
    }

    /**
     * Проверка есть ли в списке элемент, содержащий переданный текст
     *
     * @param text - текст, который надо найти
     * @return - true, если текст найден, иначе false
     */
    public Optional<Boolean> containsElementWithText(String text) {
        try {
            for (SelenideElement element: collection) {
                if(element.getText().equals(text)) {
                    return Optional.of(true);
                }
            }
            return Optional.of(false);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * Метод для получения списка строк, содержащиъ текст, хранящийся в элементах списка
     *
     * @return - список строк, хранящихся в элементах текста
     */
    public List<String> getElementsTextList() {
        List<String> textList = new ArrayList<>();
        for (SelenideElement element: collection) {
            textList.add(element.getText());
        }
        return textList;
    }

    /**
     * кликает на элеменнт коллекции находящемуся по переданному индексу
     *
     * @param index индекс элемента в коллекции
     */
    public void clickByIndex(int index) {
        try {
            collection.get(index).click();
        } catch (ElementNotFound e) {
            throw new ElementNotVisibleException("Элемент не появился на странице в течение заданного времени: " + baseElement, e);
        }
    }
}
