package pages.popUpWindows.windowsForSpaces.settings;

import elements.Buttons.LinkButton;
import pages.popUpWindows.BaseWindow;

/**
 * Окно быстрой настройки пространства, имеет только несколько вариантов настройки
 * С его помощью открывается окно со всеми настройками
 */
public class SpaceSettingsWindow extends BaseWindow {
    private final LinkButton openAllSettingsButton = LinkButton.byDataTest("project-menu-space-settings-btn");

    /**
     * Конструктор, логирует открытие окна
     */
    public SpaceSettingsWindow() {
        logger.info("space settings window is opened");
    }

    /**
     * Открывает окно со всеми настройками пространства
     * @return Экзмепляр класса окна со всеми настройками пространства
     */
    public AllSpaceSettingsWindow clickOpenAllSettingsButton() {
        openAllSettingsButton.click();
        logger.info("open all space settings button is opened");
        return new AllSpaceSettingsWindow();
    }
}
