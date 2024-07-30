package pages.popUpWindows.windowsForSpaces.settings;

import elements.Buttons.DivButton;
import elements.TextElement;
import pages.popUpWindows.BaseWindow;

/**
 * Окно настроек пространства
 * Здесь можно изменить имя, описание, приватнось цвет и иконку (аватар)
 */
public class AllSpaceSettingsWindow extends BaseWindow {
    private final TextElement descriptionContainer = TextElement.byDivDataTest
            ("create-project-modal__space-name__description");
    private final DivButton openShareSettingsButton = DivButton.byClassAndTextContains
            ("cu-create-project-modal__summary-label", "Shared with");
    private final DivButton openAvatarSettingsButton = DivButton.byClassAndTextContains
            ("cu-create-project-modal__summary-label", "Avatar");

    /**
     * Конструктор, логирует открытие окна
     */
    public AllSpaceSettingsWindow() {
        logger.info("all space settings window is opened");
    }

    /**
     * Получает описание пространства
     * @return String - описание пространства
     */
    public String getDescription() {
        String description = descriptionContainer.getText();
        logger.info("got space description: " + description);
        return description;
    }

    /**
     * Открывает окно настроек приватности
     * @return экземлпяр класса окна настроек приватности
     */
    public ShareSettingsWindow openShareSettings() {
        openShareSettingsButton.click();
        logger.info("open share settings button is clicked");
        return new ShareSettingsWindow();
    }

    /**
     * Открывает окно настроек цвета и иконки
     * @return экземпляр окна настроек цвета и иконки
     */
    public SpaceColorAndAvatarWindow openAvatarSettings() {
        openAvatarSettingsButton.click();
        logger.info("open color and avatar setting button is clicked");
        return new SpaceColorAndAvatarWindow();
    }
}
