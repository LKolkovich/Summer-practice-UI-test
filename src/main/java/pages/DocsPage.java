package pages;

import elements.Buttons.Button;

import elements.TextElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.popUpWindows.windowsForDocs.DocCreationWindow;
import pages.popUpWindows.windowsForDocs.DocOptionsWindow;

/**
 * Страница "Documents" приложения.
 * Содержит методы для взаимодействия с элементами и выполнения операций на этой странице.
 */

public class DocsPage extends SideBarPage {
    private static final Logger logger = LogManager.getLogger(DocsPage.class);

    private final String toggleMarkerXpath = "(//div[@data-test='task-row-toggle__marker'])[%d]";
    private final String noDocsText = "Let’s create your first ClickUp Doc!";
    private final String docsOptionsButtonClass = "toggle cu-dropdown__toggle ng-star-inserted";
    private final String docTextElementXpath = "(//span[@class='name-label ng-star-inserted'])[%d]";
    private final String actionsBarXpath = "//cu-bulk-actions-bar[@class='visible']";

    private final Button createDocButton = Button.byClass("app-docs ng-star-inserted");
    private final Button selectAllButton = Button.byFullXPath("//span[@class='table-row-checkbox-marker']");
    private final Button deleteButton = Button.byData("bulk-actions-bar__delete-btn");
    private final Button deselectButton = Button.byClass("deselect");
    private final Button archiveTabButton = Button.byData("widget-quick-filters__doc-tab__6__inactive");

    /**
     * Ожидание появления меню действий
     */
    private void actionBarShouldBeVisible() {
        TextElement.byFullXPath(actionsBarXpath).shouldExist();
    }

    /**
     * Метод нажатия на кнопку создания документа
     *
     * @return экземпляр класса окна создания документа
     */
    public DocCreationWindow clickCreateDocButton() {
        createDocButton.click();
        logger.info("Create button clicked");
        return new DocCreationWindow();
    }

    /**
     * Выбирает документ по его номеру в таблице
     * Нумерация начинается с 1 сверху вниз
     *
     * @param index - номер документа
     */
    public void selectDocByIndex(int index) {
        Button btn = Button.byFullXPath(String.format(toggleMarkerXpath, index));
        btn.hover();
        btn.click();
        logger.info(String.format("Selected document with index %d", index));
    }

    /**
     * Ожидание загрузки таблицы
     */
    public void waitTable() {
        Button.byFullXPath(String.format(toggleMarkerXpath, 1)).shouldExist();
    }

    /**
     * Выбирает все документы
     */
    public void selectAllDocs() {
        selectAllButton.shouldExist();
        selectAllButton.hover();
        selectAllButton.click();
        logger.info("All documents selected");
    }

    /**
     * Отменяет выбор документов
     */
    public void clickDeselectButton() {
        actionBarShouldBeVisible();
        deselectButton.click();
        deselectButton.shouldDisappear();
        logger.info("All document deselected");
    }

    /**
     * Нажатие на кнопку удаления документов
     */
    public void clickDeleteButton() {
        actionBarShouldBeVisible();
        deleteButton.shouldExist();
        deleteButton.click();
        deleteButton.shouldDisappear();
        logger.info("Delete documents button clicked");
    }

    /**
     * Возвращает число выбранных документов,
     * указанных на кнопке 'Deselect'
     *
     * @return число выбранных документов
     */
    public int selectedDocsNumber() {
        actionBarShouldBeVisible();
        int number = Integer.parseInt(deselectButton.getText().split(" ")[0]);
        logger.info(String.format("%d documents selected", number));
        return number;
    }

    /**
     * Выполняет проверку, удалены ли все документы,
     * путём проверки существования соответствующего текста.
     *
     * @return true, если документов нет, иначе false
     */
    public boolean isDocsDeleted() {
        try {
            TextElement noDocsTextElement = TextElement.byTextPart(noDocsText);
            noDocsTextElement.shouldBeVisible();
            logger.info("There's no documents");
            return true;
        } catch (Exception e) {
            logger.info("Documents exist");
            return false;
        }

    }

    /**
     * Открытие меню дополнительных опций для документа по его номеру
     * Нумерация начинается с 1 сверху вниз
     *
     * @param index - номер документа
     * @return экземпляр класса окна дополнительных опций
     */
    public DocOptionsWindow clickOptionsButton(int index) {
        Button optionsButton= Button.byClass(docsOptionsButtonClass, index - 1);
        optionsButton.click();
        logger.info(String.format("Options button clicked for document with index %d", index));
        return new DocOptionsWindow();
    }

    /**
     * Возвращает название документа по его номеру
     * Нумерация начинается с 1 сверху вниз
     *
     * @param index - номер документа
     * @return название документа
     */
    public String getDocName(int index) {
        TextElement docNameTextElement = TextElement.byFullXPath(String.format(docTextElementXpath, index));
        return docNameTextElement.getText();
    }

    /**
     * Переход во вкладку архивированных документов
     */
    public void switchToArchiveTab() {
        archiveTabButton.click();
        logger.info("Switched to archive tab");
    }
}
