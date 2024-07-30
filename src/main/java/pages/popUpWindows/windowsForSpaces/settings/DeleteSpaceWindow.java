package pages.popUpWindows.windowsForSpaces.settings;

import elements.Buttons.Button;
import elements.Input;
import pages.popUpWindows.BaseWindow;

/**
 * Окно, появляющееся для подтверждения удаления пространства
 */
public class DeleteSpaceWindow extends BaseWindow {
    private final Button submitDeleteSpaceButton = Button.byClass("confirm-button ng-star-inserted");

    /**
     * Конструктор, логирует открытие окна
     */
    public DeleteSpaceWindow() {
        logger.info("delete confirmation window is opened");
    }

    /**
     * Заполняет поле для подтверждения удаления пространства
     * (Сайт требует ввести название пространства для подтверждения удаления)
     * @param spaceName - название удаляемого пространства
     * @return возвращает this для удобной обработки в дальнейшем
     */
    public DeleteSpaceWindow fillSubmitDeleteSpaceInput(String spaceName) {
        Input submitDeleteSpaceInput = Input.byPlaceholder(spaceName);
        submitDeleteSpaceInput.fill(spaceName);
        logger.info("name of space is entered: " + spaceName);
        return this;
    }

    /**
     * Нажимает кнопку подтверждения удаления
     */
    public void clickSubmitDeleteSpaceButton() {
        submitDeleteSpaceButton.click();
        logger.info("submit delete button is clicked");
    }
}
