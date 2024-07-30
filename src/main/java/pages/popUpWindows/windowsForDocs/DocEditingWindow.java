package pages.popUpWindows.windowsForDocs;

import elements.Buttons.DivButton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс, представляющий окно редактирования документа
 */
public class DocEditingWindow {
    private static final Logger logger = LogManager.getLogger(DocEditingWindow.class);
    private final DivButton title = DivButton.byClass("cu-dashboard-doc-title__text ng-star-inserted");
    private final DivButton closeButton = DivButton.byDataTest("dashboard-doc-container__topbar-button-close-doc");

    /**
     * Возвращает название редактируемого документа,
     * указанного в поле title
     *
     * @return название документа
     */
    public String getTitle() {
        String titleText = title.getText();
        logger.info(String.format("Title received: %s", titleText));
        return titleText;
    }

    /**
     * Нажатие на кнопку закрытия окна
     */
    public void clickCloseButton() {
        closeButton.click();
        logger.info("Document closed");
    }
}
