package pages;


import elements.Buttons.LinkButton;
import pages.popUpWindows.windowForTasks.TasksWindow;

import elements.Buttons.Button;

/**
 * Страница "Домашняя страница" приложения.
 * Содержит методы для взаимодействия с элементами и выполнения операций на этой странице.
 */

public class HomePage extends SideBarPage {

    /**
     * Кнопка для открытия профиля пользователя. Используется для проверки авторизации.
     * Если кнопка профиля есть, значит авторизация прошла успешно
     */
    private final Button profileButton = Button.byClass("user-main-settings-menu__toggle cu-dropdown__toggle");

    /**
     * Кнопка для закрытия окна с заданиями
     */
    private final Button taskButton = Button.byClass("app-myTasks ng-star-inserted");

    private final LinkButton docsPageButton = LinkButton.byDataTest("simple-bar-item-docs");

    /**
     * Проверяет, отображается ли кнопка профиля пользователя.
     *
     * @return trye, если отображается, иначе false
     */
    public boolean checkProfileButton(){
        try {
            return profileButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Открытие окна с задачами
     *
     * @return Экземпляр класса окна задач
     */
    public TasksWindow clickTaskButton(){
        taskButton.click();
        logger.info("Task window opened");
        return new TasksWindow();
    }

    /**
     * Открытие страницы с документами
     *
     * @return Экземпляр класса страницы документов
     */
    public DocsPage clickDocsPageButton() {
        docsPageButton.click();
        return new DocsPage();
    }

    /**
     * Открытие страницы с документа с дополнительным ожиданием прогрузки таблицы
     *
     * @return Экземпляр класса страницы документов
     */
    public DocsPage clickDocsPageButtonAndWaitTable() {
        docsPageButton.click();
        DocsPage docsPage = new DocsPage();
        docsPage.waitTable();
        return docsPage;
    }
}
