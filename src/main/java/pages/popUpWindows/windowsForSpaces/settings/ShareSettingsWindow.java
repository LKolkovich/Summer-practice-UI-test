package pages.popUpWindows.windowsForSpaces.settings;

import elements.TextElement;
import pages.popUpWindows.BaseWindow;

/**
 * Окно, используемое для настроек приватности уже созданного пространства.
 *  * В тестах используется для получения этих данных.
 */
public class ShareSettingsWindow extends BaseWindow {
    private final TextElement privateSpaceText = TextElement.byFullXPath("//div[@data-test='privacy-settings__btn']//div[contains(@class, 'text')]");

    /**
     * Конструктор, логирует открытие окна
     */
    public ShareSettingsWindow() {
        logger.info("space share settings window is opened");
    }

    /**
     * Получает информацию о приватности пространства.
     * Если пространство приватно - из описания.
     * Если не приватно - из кнопки "Make Private"
     * @return описание приватности
     */
    public String getPrivacyDescription() {
        try {
            String settings;
            settings = privateSpaceText.getText();
            logger.info("got privacy settings button text: " + settings);
            return settings;
        } catch (Exception e) {
            logger.info("can`t get privacy settings");
            logger.info(e.getMessage());
            return "";
        }
    }
}
