package tests;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pages.DocsPage;
import pages.HomePage;
import pages.popUpWindows.windowsForDocs.DocCreationWindow;
import pages.popUpWindows.windowsForDocs.DocEditingWindow;
import pages.popUpWindows.windowsForDocs.DocOptionsWindow;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DocsTests extends BaseTest {
    private final String[] docNamesList = {"Doc1", "Doc2", "DooooocWithVeeeryLongName"};
    private final String DEFAULT_DOC_NAME = "Doc";
    private final String DUPLICATED_DOC_NAME = "%s (copy)";
    private final int firstDocsIndex = 1;

    /**
     * Метод создания документа с заданным названием со страницы документов, что приводит
     * к появлению окна редактирования документа
     *
     * @param docsPage - страница документов
     * @param docName - название документа
     * @return - экземпляр класса окна редактирования документа
     */
    private DocEditingWindow createDocument(DocsPage docsPage, String docName) {
        DocCreationWindow docCreationWindow = docsPage.clickCreateDocButton();
        docCreationWindow.fillTitle(docName);
        return docCreationWindow.clickCreateButton();
    }

    /**
     * Создание документов с названиями из списка docNamesList
     */
    @Order(1)
    @Test
    public void createDocsTest() {
        HomePage homePage = auth();
        DocsPage docsPage = homePage.clickDocsPageButton();
        for (String docName : docNamesList) {
            DocEditingWindow docEditingWindow = createDocument(docsPage, docName);
            assertEquals(docName, docEditingWindow.getTitle(), "Error during document creation");
            docEditingWindow.clickCloseButton();
        }
    }

    /**
     * Создание документа без названия
     */
    @Order(2)
    @Test
    public void createNamelessDocTest() {
        String docName = "";
        HomePage homePage = auth();
        DocsPage docsPage = homePage.clickDocsPageButton();
        DocEditingWindow docEditingWindow = createDocument(docsPage, docName);
        docEditingWindow.clickCloseButton();
        docsPage.refresh();
        assertEquals(DEFAULT_DOC_NAME, docsPage.getDocName(firstDocsIndex),
                "Error during default document creation");
    }

    /**
     * Дублирование (копирование) документа
     */
    @Order(3)
    @Test
    public void DuplicateDocTest() {
        HomePage homePage = auth();
        DocsPage docsPage = homePage.clickDocsPageButton();
        DocOptionsWindow docOptionsWindow = docsPage.clickOptionsButton(firstDocsIndex);
        docOptionsWindow.clickDuplicateButton();
        docsPage.refresh();
        assertEquals(String.format(DUPLICATED_DOC_NAME, docsPage.getDocName(firstDocsIndex + 1)),
                docsPage.getDocName(firstDocsIndex));
    }

    /**
     * Перемещение документа в архив
     */
    @Order(4)
    @Test
    public void ArchiveDocTest() {
        HomePage homePage = auth();
        DocsPage docsPage = homePage.clickDocsPageButton();
        String docName = docsPage.getDocName(firstDocsIndex);
        DocOptionsWindow docOptionsWindow = docsPage.clickOptionsButton(firstDocsIndex);
        docOptionsWindow.clickArchiveButton();
        docsPage.switchToArchiveTab();
        assertEquals(docsPage.getDocName(firstDocsIndex), docName);
    }

    /**
     * Удаление последнего созданного документа
     */
    @Order(5)
    @Test
    public void deleteLastDocTest() {
        HomePage homePage = auth();
        DocsPage docsPage = homePage.clickDocsPageButtonAndWaitTable();
        docsPage.selectAllDocs();
        int docsNumberBefore = docsPage.selectedDocsNumber();
        docsPage.clickDeselectButton();
        docsPage.selectDocByIndex(1);
        docsPage.clickDeleteButton();
        docsPage.selectAllDocs();
        int docsNumberAfter = docsPage.selectedDocsNumber();
        assertEquals(docsNumberAfter, docsNumberBefore - 1);
    }

    /**
     * Удаление всех существующих документов
     */
    @Order(6)
    @Test
    public void deleteAllDocsTest() {
        HomePage homePage = auth();
        DocsPage docsPage = homePage.clickDocsPageButtonAndWaitTable();
        docsPage.selectAllDocs();
        docsPage.clickDeleteButton();
        assertTrue(docsPage.isDocsDeleted());
    }

}
