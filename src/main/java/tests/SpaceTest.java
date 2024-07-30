package tests;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pages.HomePage;
import pages.SpacePage;
import utils.Space;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестирование создания и удаления пространств с разными параметрами
 * Также присутствуют тесты на обработку некорректного ввода
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpaceTest extends BaseTest {
    private static final String BASE_SPACE_NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String PRIVATE_SPACE_NAME = "private";
    private static final String WORKFLOW_SPACE_NAME = "workflow";
    private static final String NON_PRIVATE_SPACE_NAME = "non private";
    private static final String EXPECTED_COLOR = "106, 133, 255";
    private static final String EXPECTED_ICON = "wifi";
    private static final String CUSTOM_COLOR_SPACE_NAME = "custom";
    private static final String EMPTY_STRING = "";
    private static final List<String> EXPECTED_VIEWS = new ArrayList<>(Arrays.asList("Overview", "List", "Board",
            "Timeline", "Table", "Workload"));
    private static final String NON_PRIVATE_SPACE_RESULT = "Make Private";
    private static final String EMPTY_NAME_TEXT = "Space name is required";
    private static final String ALREADY_EXISTS_NAME_TEXT = "Space name already exists";
    private static final String PRIVATE_SPACE_RESULT = "Make Public";
    private static final int PRODUCT_ENGINEERING_WORKFLOW_NUM = 3;


    /**
     * Создание пространства только с названием
     * Пространство должно быть успешно создано
     */
    @Order(1)
    @Test
    public void createSpaceTest() {
        logger.info("create space test starts");
        HomePage homePage = auth();
        homePage.createSpace(new Space(BASE_SPACE_NAME));
        assertTrue(homePage.isSpaceExists(BASE_SPACE_NAME),"Error during creation of space");
    }


    /**
     * Попытка создания пространства без названия.
     * Приложение должно отказать в создании, сообщив об ошибке
     */
    @Test
    public void createSpaceWithoutNameTest() {
        logger.info("create space without name test starts");
        HomePage homePage = auth();
        Pair<String, SpacePage> result = homePage.createSpace(new Space(EMPTY_STRING));
        String error = result.getLeft();
        assertEquals(EMPTY_NAME_TEXT, error, "wrong handling of empty name");
    }


    /**
     * Попытка создания пространства с названием, которое уже используется.
     * Приложение должно отказать в создании, сообщив об ошибке
     */
    @Order(2)
    @Test
    public void createSpaceWithSameNameTest() {
        logger.info("create space with same name test starts");
        HomePage homePage = auth();
        if (!(homePage.isSpaceExists(BASE_SPACE_NAME))) {
            logger.fatal("wrong start: no exists space with name = " + BASE_SPACE_NAME);
            return;
        }

        Pair<String, SpacePage> result = homePage.createSpace(new Space(BASE_SPACE_NAME, EMPTY_STRING, false));
        String error = result.getLeft();
        assertEquals(ALREADY_EXISTS_NAME_TEXT, error, "wrong handling of already exists name");
    }


    /**
     * Создание приватного пространства
     */
    @Order(3)
    @Test
    public void createPrivateSpace() {
        HomePage homePage = auth();
        Pair<String, SpacePage> result = homePage.createSpace(new Space(PRIVATE_SPACE_NAME, EMPTY_STRING,
                true));
        SpacePage spacePage = result.getRight();
        spacePage.openSpace(PRIVATE_SPACE_NAME);

        String privacySettings = spacePage.getSpacePrivacySettings();
        assertEquals(PRIVATE_SPACE_RESULT, privacySettings, "error during creation of private space");
    }


    /**
     * Создание не приватного пространства
     */
    @Order(4)
    @Test
    public void createNonPrivateSpace() {
        HomePage homePage = auth();
        if (homePage.isSpaceExists(PRIVATE_SPACE_NAME)) {
            SpacePage spacePage = homePage.openSpace(PRIVATE_SPACE_NAME);
            spacePage.deleteSpace(new Space(PRIVATE_SPACE_NAME));
            spacePage.refresh();
        }

        Pair<String, SpacePage> result = homePage.createSpace(new Space(NON_PRIVATE_SPACE_NAME, EMPTY_STRING,
                false));
        SpacePage spacePage = result.getRight();
        spacePage.openSpace(NON_PRIVATE_SPACE_NAME);

        String privacySettings = spacePage.getSpacePrivacySettings();
        assertEquals(NON_PRIVATE_SPACE_RESULT, privacySettings, "error during creation of private space");
    }


    /**
     * Создание пространства с заданным именем и последующее его удаление
     * Пространство должно быть создано, а затем удалено
     */
    @Test
    public void deleteSpaceTest() {
        logger.info("delete space test starts");
        HomePage homePage = auth();
        if (!homePage.isSpaceExists(BASE_SPACE_NAME)) {
            homePage.createSpace(new Space(BASE_SPACE_NAME));
        }
        SpacePage spacePage = homePage.openSpace(BASE_SPACE_NAME);
        spacePage.deleteSpace(new Space(BASE_SPACE_NAME));
        spacePage = spacePage.refresh();

        assertFalse(spacePage.isSpaceExists(BASE_SPACE_NAME),"Error during delete of space");
    }


    /**
     * Создание пространства с выбором workflow
     * Пространство должно быть успешно создано, нужные view созданы
     */
    @Test
    public void createProductEngineeringSpaceTest() {
        logger.info("create with custom workflow test starts");
        HomePage homePage = auth();
        Pair<String, SpacePage> result = homePage.createSpace(new Space(WORKFLOW_SPACE_NAME,
                PRODUCT_ENGINEERING_WORKFLOW_NUM));
        SpacePage spacePage = result.getRight();
        spacePage.openSpace(WORKFLOW_SPACE_NAME);

        List<String> views = spacePage.getViews();
        assertEquals(EXPECTED_VIEWS, views, "error during creation with workflow");
    }


    /**
     * Создание пространства с названием и описанием
     * Пространство должно быть создано, описание - сохранено
     */
    @Test
    public void createSpaceWithDescription() {
        logger.info("create space with description test starts");
        HomePage homePage = auth();
        Pair<String, SpacePage> result = homePage.createSpace(new Space(DESCRIPTION, DESCRIPTION, false));
        SpacePage spacePage = result.getRight();
        if (spacePage == null) {
            logger.error("error during creation of space");
            return;
        }
        spacePage.openSpace(DESCRIPTION);
        String descriptionFromPage;
        descriptionFromPage = spacePage.getSpaceDescription();

        assertEquals(DESCRIPTION, descriptionFromPage, "wrong description");
    }


    /**
     * Создание пространства с выбором иконки(аватара) и цвета
     * Пространство должно быть успешно создано, иконка и цвет - сохранены
     */
    @Test
    public void createSpaceWithCustomIconAndColor() {
        HomePage homePage = auth();
        Pair<String, SpacePage> result = homePage.createSpace(new Space(CUSTOM_COLOR_SPACE_NAME, EMPTY_STRING,
                EXPECTED_COLOR, EXPECTED_ICON));
        SpacePage spacePage = result.getRight();
        spacePage.openSpace(CUSTOM_COLOR_SPACE_NAME);

        Pair<String, String> colorAndIcon = spacePage.getColorAndIcon();
        String realColor = colorAndIcon.getLeft();
        String realIcon = colorAndIcon.getRight();
        assertTrue((realColor.equals(EXPECTED_COLOR) && realIcon.equals(EXPECTED_ICON)),
                "error during customising of space");
    }

}
