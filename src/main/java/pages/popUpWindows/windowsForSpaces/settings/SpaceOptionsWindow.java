package pages.popUpWindows.windowsForSpaces.settings;

import elements.Buttons.LinkButton;
import pages.popUpWindows.BaseWindow;

/**
 * Окно, открывающееся при нажатии на опции пространства (три точки около его названия)
 * Используется для открытия настроек и удаления пространства
 */
public class SpaceOptionsWindow extends BaseWindow {
    private final LinkButton deleteSpaceButton = LinkButton.byClass("nav-menu-item nav-menu-item__delete_space ng-star-inserted");
    private final LinkButton openSpaceSettingsButton = LinkButton.byDataTest("project-menu__space-settings-item");

    /**
     * Конструктор, логирует открытие окна
     */
    public SpaceOptionsWindow() {
        logger.info("space options window is opened");
    }

    /**
     * Нажимает кнопку "удалить пространство"
     * @return экземляр класса окна удаления пространства
     */
    public DeleteSpaceWindow clickDeleteSpaceButton() {
        deleteSpaceButton.click();
        logger.info("delete space button clicked");
        return new DeleteSpaceWindow();
    }

    /**
     * Открывает настройки пространства
     * @return экземпляр окна настроек пространства
     */
    public SpaceSettingsWindow clickOpenSpaceSettings() {
        openSpaceSettingsButton.click();
        logger.info("open space setting button clicked");
        return new SpaceSettingsWindow();
    }
}
