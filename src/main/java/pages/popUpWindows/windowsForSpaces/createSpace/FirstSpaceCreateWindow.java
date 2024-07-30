package pages.popUpWindows.windowsForSpaces.createSpace;

import elements.Buttons.Button;
import elements.Buttons.DivButton;
import elements.Input;
import elements.TextElement;
import pages.popUpWindows.BaseWindow;

/**
 * Первое окно, появляющееся при создании пространства
 * В нем настраиваются название, описание, приватность, цвет и иконка (аватар)
 */
public class FirstSpaceCreateWindow extends BaseWindow {
    private final Input spaceNameInput = Input.byDataAndIndex("create-space-details__input", 0);
    private final Input spaceDescriptionInput = Input.byDataAndIndex("create-space-details__input", 1);
    private final Button continueCreationButton = Button.byData("create-space-details__continue-button");

    private final TextElement errorContainer = TextElement.byFullXPath("//div[@class='error-message ng-star-inserted']" +
            "//span[@class='ng-star-inserted']");

    private final Button switchAccessibilityButton = Button.byData("switch__toggle-body");

    private final Button openAndCloseCustomiseButton = Button.byClass("icon-button cu-dropdown__toggle");
    private final String pickColorString = "//button[@class='cu-color-picker-panel__preset-color']" +
            "[@style='color: rgb(%s);']";
    private final String pickIconString = "//button[@class='cu-avatar-picker__icons-item'][@aria-label='%s']";
    private final DivButton uploadIcon = DivButton.byDataTest("avatar-picker__icons-add-body");
    private final Button closeWindowButton = Button.byData("modal-close-btn");

    /**
     * Конструктор, логирует открытие окна
     */
    public FirstSpaceCreateWindow() {
        logger.info("first creation window is opened");
    }

    /**
     * Вводит название пространства в поле для названия
     * @param name - название пространства
     */
    public void fillSpaceNameInput(String name) {
        spaceNameInput.fill(name);
        logger.info("name of space is entered, name = " + (name.isEmpty() ? "empty name" : name));
    }

    /**
     * Вводит описание пространства в поле для описания
     * @param description - описание пространства
     */
    public void fillSpaceDescriptionInput(String description) {
        spaceDescriptionInput.fill(description);
        logger.info("description of space is entered, description = " + (description.isEmpty() ? "empty description" : description));

    }

    /**
     * Нажимает кнопку "continue", чтобы продолжить создание пространства
     * Переходит к другому окну
     * @return экземпляр класса второго окна для создания пространства
     */
    public SecondSpaceCreateWindow clickContinueCreationButton() {
        continueCreationButton.click();
        logger.info("continue button clicked");
        return new SecondSpaceCreateWindow();
    }


    /**
     * Проверяет появилась ли информация об ошибке ввода названия
     * @return true - ошибка появилась, false - ошибки нет
     */
    public boolean isError() {
        boolean error = errorContainer.isDisplayed();
        if (error) {
            logger.info("name is right");
        } else {
            logger.info("name is wrong");
        }
        return error;
    }

    /**
     * Получает строку с информацией об ошибке, если ошибка произошла
     * @return String с информацией об ошибке, если ошибка произошла, иначе пустая строка
     */
    public String getCreateErrorText() {
        if (errorContainer.isDisplayed()) {
            logger.info("name error: " + errorContainer.getText());
            return errorContainer.getText();
        }
        logger.info("no name error");
        return "";
    }

    /**
     * нажимает переключатель настройки приватности
     */
    public void clickPrivacySettingsButton() {
        switchAccessibilityButton.click();
        logger.info("private switch is toggled, space is made private");
    }

    /**
     * открвыает панель кастомизации названия и иконки пространства
     */
    public void clickOpenAndCloseCustomiseButton() {
        openAndCloseCustomiseButton.click();
        logger.info("customization button is clicked, customization window is open");
    }

    /**
     * Для этого нажимает "загрузить свою иконку", а затем закрвыает появивщееся окно
     * Используется для закрытия панели кастомизации, потому что лучшего решения найдено не было
     * Необходимо, так как иначе закрыть окно кастомизации методами Selenide не получается
     */
    public void closeCustomiseWindow() {
        uploadIcon.click();
        closeWindowButton.click();
        logger.info("customise window is closed");
    }

    /**
     * Выбирает цвет по заднному набору rgb
     * @param color 0 строка в формате "r, g, b"
     */
    public void pickColor(String color) {
        Button pickColorButton = Button.byFullXPath(String.format(pickColorString, color));
        pickColorButton.click();
        logger.info("picked color = " + color);
    }

    /**
     * Выбирает иконку по её названию
     * @param icon - название иконки
     */
    public void pickIcon(String icon) {
        Button pickIconButton = Button.byFullXPath(String.format(pickIconString, icon));
        pickIconButton.click();
        logger.info("picked icon = " + icon);
    }

}
