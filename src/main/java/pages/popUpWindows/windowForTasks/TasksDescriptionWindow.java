package pages.popUpWindows.windowForTasks;


import elements.Buttons.Button;
import elements.Buttons.DivButton;
import elements.TextElement;
import pages.popUpWindows.BaseWindow;


import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Этот класс представляет собой окно деталий задач в веб-приложении.
 * Он предоставляет методы для взаимодействия с описаниями и проверки их наличия.
 */
public class TasksDescriptionWindow extends BaseWindow {

    private static final Logger logger = LogManager.getLogger(TasksDescriptionWindow.class);

    private final Button closeTaskDescriptionButton = Button.byData("task-close-v3");
    private final Button taskSettingsButton = Button.byData("task-view-header__task-settings");
    private final Button markDoneButton = Button.byData("status-button__checkmark");
    private final Button addDescriptionButton = Button.byData("dropdown-list-item__blank");

    private final DivButton deleteTaskButton = DivButton.byDataTest("dropdown-list-item__Delete");
    private final DivButton priotiryButton = DivButton.byDataTest("task-hero-section-priority__row-data");

    private final TextElement description = TextElement.byDivClass("ql-block");
    private final TextElement emptyPriority = TextElement.bySpanClass(
            "cu-task-hero-section-priority__empty-label ng-star-inserted");

    private final String textForEmptyPriority = "Empty";
    private final String taskPriorityXpath = "priorities-view__item-label-";
    private final String listPriorityXpath = "priorities-list__item-";

    /**
     * Проверяет наличие описания задачи.
     *
     * @param descriptionText Текст описания.
     * @param descriptionAdded Флаг, указывающий было ли описание добавлено.
     * @return true, если описание присутствует, иначе false.
     */
    public boolean isDescriptionPresent(String descriptionText, Boolean descriptionAdded) {
        if(descriptionAdded){
            logger.info("Description added block");
            return description.isExists() && description.has(Condition.text(descriptionText));
        }else {
            logger.info("Description was not added block");
            return addDescriptionButton.isExists();
        }
    }

    /**
     * Проверяет, установлен ли приоритет задачи.
     *
     * @param priority Приоритет задачи.
     * @param priorityAdded Флаг, указывающий был ли приоритет добавлен.
     * @return true, если приоритет установлен, иначе false.
     */
    public boolean isPrioritySet(String priority, Boolean priorityAdded) {
        if (priorityAdded){
            DivButton priorityButton = DivButton
                        .byDataTest(taskPriorityXpath + priority);
            logger.info("Priority added block");
            return priorityButton.isExists();
        }else{
            logger.info("Priority was not added block");
            return emptyPriority.isExists() && emptyPriority.has(Condition.text(textForEmptyPriority));
        }
    }

    /**
     * Нажимает кнопку выполнения задачи.
     */
    public void clickDoneButton(){
        markDoneButton.click();
        logger.info("Task done, button clicked");
    }

    /**
     * Нажимает кнопку приоритета.
     */
    public void clickPriorityButton(){
        priotiryButton.click();
        logger.info("Priority button clicked");
    }

    /**
     * Устанавливает приоритет задачи.
     *
     * @param priority Приоритет задачи.
     */
    public void setPriority(String priority){
        Button addPriorityButton = Button.byData(listPriorityXpath + priority);
        addPriorityButton.click();
        logger.info("Priority set - " + priority);
    }

    /**
     * Закрывает окно описания задачи.
     */
    public void closeWindow(){
        closeTaskDescriptionButton.click();
        logger.info("Description window closed");
    }

    /**
     * Открывает настройки задачи.
     */
    public void openTaskSettings(){
        taskSettingsButton.click();
        logger.info("Task settings opened");
    }

    /**
     * Нажимает кнопку удаления задачи.
     */
    public void clickDeleteButton(){
        deleteTaskButton.click();
        logger.info("Task deleted");
    }

}
