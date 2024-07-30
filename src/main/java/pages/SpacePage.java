package pages;

import elements.Buttons.Button;
import elements.ElementList;
import pages.popUpWindows.windowsForSpaces.settings.AllSpaceSettingsWindow;
import pages.popUpWindows.windowsForSpaces.settings.DeleteSpaceWindow;
import pages.popUpWindows.windowsForSpaces.settings.SpaceColorAndAvatarWindow;
import pages.popUpWindows.windowsForSpaces.settings.SpaceOptionsWindow;
import org.apache.commons.lang3.tuple.Pair;
import utils.Space;
import java.util.List;


/**
 * Страница работы с "рабочими пространствами"
 * Предоставляет основные методы для работы с пространствами
 */
public class SpacePage extends SideBarPage {
    private final Button openSpaceOptionsButton = Button.byFullXPath("//cu-ellipsis-button[@data-test='location-menu__ellipsis-button']//button");
    private final ElementList views = ElementList.byDivClass("cu-data-view-item__name-text");

    /**
     * Нажатие кнопки, открывающей окно с опциями взаимодействия с пространством
     *
     * @return экзмпляр класса SpaceOptionsWindow, описывающего логику работы с окном
     */
    public SpaceOptionsWindow clickOpenSpaceOptionsButton() {
        openSpaceOptionsButton.click();
        logger.info("open space options button is clicked");
        return new SpaceOptionsWindow();
    }

    /**
     * Открытие окна "все настройки" для пространства
     * Для этого последовательно открываются несколько окон: SpaceOptionsWindow, SpaceSettingsWindow,
     * и затем AllSpaceSettingsWindow
     *
     * @return экзмпляр класса AllSpaceSettingsWindow, описывающего логику работы с окном
     */
    public AllSpaceSettingsWindow openSpaceSettings() {
        return clickOpenSpaceOptionsButton().clickOpenSpaceSettings().clickOpenAllSettingsButton();
    }

    /**
     * Получения описания пространства из окна "все настройки", для этого:
     * 1) вызывается метод класса SpacePage для получения окна
     * 2) вызывается метод класса окна для получения описания
     *
     * @return String - описание пространства
     */
    public String getSpaceDescription() {
        return openSpaceSettings().getDescription();
    }

    /**
     * Получения описания приватности пространства
     *
     * @return String - информация по приватности пространства для дальнейшей обработки
     */
    public String getSpacePrivacySettings() {
        return openSpaceSettings().openShareSettings().getPrivacyDescription();
    }


    /**
     * В окне "все настройки" открывает окно выбора цвета и иконки, получает инфомарцию о выбранных
     *
     * @return Pair<Цвет, Иконка>
     */
    public Pair<String, String> getColorAndIcon() {
        SpaceColorAndAvatarWindow window = openSpaceSettings().openAvatarSettings();
        return Pair.of(window.getColor(), window.getIcon());
    }

    /**
     * Получает список всех view, которые есть в пространстве
     *
     * @return List<String> - все view в пространстве
     */
    public List<String> getViews() {
        return views.getElementsTextList();
    }


    /** Удаляет пространство с заданным именем, последовательно проделывая все требуемые шаги
     *
     * * @param space - обертка над аргументами, класс Space, используется для получения имени пространства
     */
    public void deleteSpace(Space space) {
        logger.info("deleting of space " + space.getName());

        DeleteSpaceWindow deleteSpaceWindow = clickOpenSpaceOptionsButton().clickDeleteSpaceButton();
        deleteSpaceWindow.fillSubmitDeleteSpaceInput(space.getName()).clickSubmitDeleteSpaceButton();
    }
}
