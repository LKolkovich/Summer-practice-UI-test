package pages.popUpWindows.windowsForDocs;

import elements.Buttons.Button;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.popUpWindows.BaseWindow;

/**
 * Класс, представляющий окно дополнительных действий над документом
 */
public class DocOptionsWindow extends BaseWindow {
    private static final Logger logger = LogManager.getLogger(DocOptionsWindow.class);
    private final Button duplicateButton = Button.byFullXPath("//span[text()='Duplicate']//parent::button");
    private final Button archiveButton = Button.byFullXPath("//span[text()='Archive']//parent::button");

    /**
     * Нажатие на кнопку дублирования документа
     */
    public void clickDuplicateButton() {
        duplicateButton.click();
        logger.info("Document duplicated");
    }

    /**
     * Нажатие на кнопку перемещения документа в архив
     */
    public void clickArchiveButton() {
        archiveButton.click();
        logger.info("Document archived");
    }
}
