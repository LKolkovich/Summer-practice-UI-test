package pages.popUpWindows.windowsForSpaces.createSpace;

import com.codeborne.selenide.ElementsCollection;
import elements.Buttons.Button;
import elements.ElementList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.popUpWindows.BaseWindow;

import static com.codeborne.selenide.Selenide.$$x;

/**
 * Второе окно, появляющееся при создании пространства
 * В нём выбираются базовые настройки исходя из workflow пользователя
 */
public class SecondSpaceCreateWindow extends BaseWindow {
    private final Button createSpaceButton = Button.byData("create-test-workflow__create-space-button");
    private final String workFlowString = "//cu-create-space-solution-card[@class='ng-star-inserted']";

    /**
     * Конструктор, логирует открытие окна
     */
    public SecondSpaceCreateWindow() {
        logger.info("second creation window is opened");
    }

    /**
     * Нажимает кнопку завершения создания пространства
     */
    public void clickCreateSpaceButton() {
        createSpaceButton.click();
        logger.info("create space button clicked");
        createSpaceButton.shouldDisappear();
    }

    /**
     * Выбирает Workflow из 4 предложенных вариантов
     * @param workflowNum - номер workflow
     */
    public void pickWorkFlow(int workflowNum) {
        ElementList workflows = ElementList.byFullXPath(workFlowString);
        workflows.clickByIndex(workflowNum);
        logger.info("worklow picked, number: " + workflowNum);
    }
}
