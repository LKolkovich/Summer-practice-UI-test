package pages.popUpWindows.windowsForDocs;

import elements.Buttons.Button;
import elements.Input;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.popUpWindows.BaseWindow;

/**
 * Класс, представляющий окно создания документа
 */

public class DocCreationWindow extends BaseWindow {
    private static final Logger logger = LogManager.getLogger(DocCreationWindow.class);

    private final Input titleInput = Input.byPlaceholder("Name this Doc...");
    private final Button createButton = Button.byData("create-view-buttons_button");

    /**
     * Заполняет название документа заданной строкой
     *
     * @param docName - название документа
     */
    public void fillTitle(String docName) {
        titleInput.shouldBeVisible();
        titleInput.fill(docName);
        logger.info(String.format("Title is filled with the name '%s'", docName));
    }

    /**
     * Нажатие на кнопку создания документа,
     * что приводит к появлению окна создания.
     *
     * @return экземпляр класса окна создания документа.
     */
    public DocEditingWindow clickCreateButton() {
        createButton.click();
        logger.info("Document creation submitted");
        return new DocEditingWindow();
    }
}
