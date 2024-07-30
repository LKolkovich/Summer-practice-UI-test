package pages;


import com.codeborne.selenide.SelenideElement;
import elements.Buttons.Button;
import elements.ElementList;
import elements.MouseOverElement;
import elements.TextElement;
import pages.popUpWindows.windowsForSpaces.createSpace.FirstSpaceCreateWindow;
import pages.popUpWindows.windowsForSpaces.createSpace.SecondSpaceCreateWindow;
import pages.popUpWindows.windowsForSpaces.settings.SpaceOptionsWindow;
import org.apache.commons.lang3.tuple.Pair;
import utils.Space;

import java.util.HashMap;
import java.util.Optional;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Боковая панель веб-приложения
 * Наследуется от базовой страницы и реаизует логику взаимодействия с боковой панелью
 * Является родитльским классом для всех страниц, где присутствует боковая панель
 */

public class SideBarPage extends BasePage {
    private final Button createSpaceButton = Button.byData("sidebar-header__create-new-space-button");
    public static final String optionsSidebarButtonDatetest = "project-row__ellipsis__%s";
    private static final HashMap<String, Button> sidebarSpaceOptionButtons = new HashMap<>();
    private final String openSpaceSpanString = "//span[@data-test='project-row__name__%s']";
    MouseOverElement sidebar = MouseOverElement.byClass("span", "sidebar__spaces-text");
    private final String cuRowString = "cu-project-row";
    private final String cuRowDataTest = "project-list-bar-item__link__%s']";
    private final TextElement startSpaceTextElement = TextElement.bySpanDataTest("sidebar-everything-text");


    /**
     * Нажимает кнопку (по умолчанию скрыта) создания нового пространства, для этого
     * вызывает clickWithMouseOver, в качестве аргумента передает панель, на которой появится кнопка
     *
     * @return экземпляр класса первого окна для создания пространства
     */
    public FirstSpaceCreateWindow clickCreateSpaceButton() {
        createSpaceButton.clickWithMouseOver(sidebar);
        logger.info("create new space button clicked, creation window opened");
        return new FirstSpaceCreateWindow();
    }

    /**
     * Открывает окно опций для взаимодействия с выбранным постранством, для этого
     * нажимает кнопку "опции" у выбранного пространства, используя clickWithMouseOver,
     * а также HashMap для хранения названий пространств, если их будет несколько
     *
     * @param spaceName - имя пространства, для которого надо открыть окно
     * @return окно "опции для взаимодествия с пространствами" для выбранного пространства
     */
    public SpaceOptionsWindow clickSidebarSpaceOptionButton(String spaceName) {
        if(!sidebarSpaceOptionButtons.containsKey(spaceName)) {
            sidebarSpaceOptionButtons.put(spaceName, Button.byData(String.format(optionsSidebarButtonDatetest, spaceName)));
            logger.info("new space new saved to memory");
        }
        MouseOverElement elementRow = MouseOverElement.byDataTest(cuRowString, String.format(cuRowDataTest, spaceName));
        sidebarSpaceOptionButtons.get(spaceName).clickWithMouseOver(elementRow);
        logger.info("space option window is opened");
        return new SpaceOptionsWindow();
    }


    /**
     * Проверяет существует ли пространство с заданным именем, для этого
     * проверяет существует ли кнопка для открытия пространства с заданным именем
     *
     * @param name - имя пространства
     * @return Optional, хранящее
     *                      1) true, если пространство существует
     *                      2) false, если пространство не существует
     *                      3) null, если произошла ошибка
     */
    public boolean isSpaceExists(String name) {
        startSpaceTextElement.isDisplayed();
        ElementList spaces = ElementList.bySpanClass("cu-project-row__text");
        Optional<Boolean> isExists = spaces.containsElementWithText(name);
        if (isExists.isEmpty() || !isExists.get()) {
            logger.error("space " + name + " does not exist. " + isExists);
            return false;
        }
        logger.info("space " + name + " is exists. " + isExists);
        return true;
    }

    /**
     * Создает пространства, последовательно проделывая все требуемые шаги
     * В зависимости от аргумента можтет также задавать пространству:
     *                              1) описание
     *                              2) приватность
     *                              3) цвет
     *                              4) иконку
     *
     * @param space - обертка над аргументами, класс Space
     * @return Pair<описание ошибки при создании пространства (null, если ошибки не было) для дальнейшей обработки, экземпляр SpacePage (null, если произошла ошибка)>
     */
    public Pair<String, SpacePage> createSpace(Space space) {
        startSpaceTextElement.isDisplayed();
        FirstSpaceCreateWindow spaceCreatePopUpWindow = clickCreateSpaceButton();
        spaceCreatePopUpWindow.fillSpaceNameInput(space.getName());
        spaceCreatePopUpWindow.fillSpaceDescriptionInput(space.getDescription());

        firstSpaceWindowSettings(space, spaceCreatePopUpWindow);

        SecondSpaceCreateWindow secondSpaceCreateWindow = spaceCreatePopUpWindow.clickContinueCreationButton();
        if (spaceCreatePopUpWindow.isError()) {
            logger.warn("problem during creation of space: " + spaceCreatePopUpWindow.getCreateErrorText());
            return Pair.of(spaceCreatePopUpWindow.getCreateErrorText(), null);
        }
        secondSpaceCreateWindow.pickWorkFlow(space.getWorkflow());
        secondSpaceCreateWindow.clickCreateSpaceButton();

        logger.info("second creation window is closed");
        return Pair.of("", BasePage.page(SpacePage.class)) ;

    }

    /**
     * Настройка созадния пространства в первом окне
     *
     * @param space - экземпляр класса Space для передачи парметров в методы
     * @param spaceCreatePopUpWindow - экземпляр класса окна, с которым происходит работа
     */
    private static void firstSpaceWindowSettings(Space space, FirstSpaceCreateWindow spaceCreatePopUpWindow) {
        if (space.isPrivate()) {
            spaceCreatePopUpWindow.clickPrivacySettingsButton();
        }
        if (!space.getColor().isEmpty() || !space.getIcon().isEmpty()) {
            spaceCreatePopUpWindow.clickOpenAndCloseCustomiseButton();
            if (!space.getColor().isEmpty()) {
                spaceCreatePopUpWindow.pickColor(space.getColor());
            }
            if (!space.getIcon().isEmpty()) {
                spaceCreatePopUpWindow.pickIcon(space.getIcon());
            }
            spaceCreatePopUpWindow.closeCustomiseWindow();
        }
    }

    /**
     * Открывает страницу пространства по названию пространства
     *
     * @param name - название пространства
     * @return - экземпляр класса страницы пространства
     */
    public SpacePage openSpace(String name){
        SelenideElement openSpaceSpan = $x(String.format(openSpaceSpanString, name));
        openSpaceSpan.click();
        return SpacePage.page(SpacePage.class);
    }
}
