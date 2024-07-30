package pages.popUpWindows.windowForTasks;


import elements.Buttons.Button;
import elements.Buttons.DivButton;
import elements.TextElement;
import pages.popUpWindows.BaseWindow;


import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс представляет собой окно задач.
 * Содержит методы для взаимодействия с элементами в окне задач.
 */
public class TasksWindow extends BaseWindow {
    private static final Logger logger = LogManager.getLogger(TasksWindow.class);

    private final Button todayButton = Button.byClass("cu-inbox-list__title ng-star-inserted");
    private final Button createTaskButton = Button.byData("draft-view__quick-create-create");
    private final Button switchToDoneTasksButton = Button.byId("cu-home-tabs__tab_Done");

    private final DivButton addTaskButton = DivButton.byClass("cu-inbox-list__create ng-star-inserted");
    private final DivButton descriptionButton = DivButton.byClass(
            "cu-dropdown-list-item__title-text ng-star-inserted");
    private final DivButton closeButton = DivButton.byClass(
            "cu2-right-sidebar__close-btn icon ng-star-inserted");
    private final DivButton completeButton = DivButton.byClass("cdk-visually-hidden ng-star-inserted");

    private final TextElement taskNameInput = TextElement.byTextAreaData("draft-view__title-task");
    private final TextElement descriptionInput = TextElement.byDivClass("ql-block");
    private final TextElement msgError = TextElement.byDivDataPendo("quick-create-task-enter-task-name-error");

    private final String textOnCompleteButton = " COMPLETE ";
    private final String taskNameXpath = "task-row-main__";
    private final String openTaskXpath = "task-row-main__link-text__";

    /**
     * Проверяет наличие задачи.
     *
     * @param taskName Имя задачи.
     * @param taskAdded Флаг, указывающий была ли задача добавлена.
     * @return true, если задача присутствует, иначе false.
     */
    public boolean isTaskPresent(String taskName, Boolean taskAdded) {
        if(taskAdded){
            TextElement task = TextElement.bySpanDataTest(taskNameXpath + taskName);
            try {
                task.shouldBeVisible();
            } catch (ElementNotFound e){
                logger.error("Элемент 'task' не повился");
                return false;
            }
            logger.info("Task added block");
            return task.has(Condition.text(taskName));
        }else{
            logger.info("Task was not added block");
            return msgError.isExists();
        }
    }

    /**
     * Проверяет, отмечена ли задача как выполненная.
     *
     * @param taskName Имя задачи.
     * @param taskAdded Флаг, указывающий была ли задача добавлена.
     * @return true, если задача отмечена как выполненная, иначе false.
     */
    public boolean isMarkedDone(String taskName, Boolean taskAdded) {
        if(taskAdded){
            TextElement task = TextElement.bySpanDataTest(taskNameXpath + taskName);
            try {
                task.shouldBeVisible();
            } catch (ElementNotFound e){
                logger.error("Элемент 'task' не появился");
                return false;
            }
            logger.info("Task added block");
            return task.has(Condition.text(taskName)) && completeButton.has(Condition.text(textOnCompleteButton));
        }else{
            logger.info("Task was not added block");
            return msgError.isExists();
        }
    }

    /**
     * Открывает задачу с указанным именем.
     *
     * @param taskName Имя задачи.
     * @return Объект окна описания задачи.
     */
    public TasksDescriptionWindow openTask(String taskName){
        DivButton taskButton = DivButton.byDataTest(openTaskXpath + taskName);
        taskButton.click();
        closeButton.click();
        logger.info("Task opened - " + taskName);
        return new TasksDescriptionWindow();
    }

    /**
     * Переключает окно задач на выполненные задачи.
     */
    public void clickSwitchTasks(){
        switchToDoneTasksButton.click();
        logger.info("Task window switched to done");
    }

    /**
     * Нажимает кнопку создания задачи.
     */
    public void clickCreateTaskButton(){
        createTaskButton.click();
        logger.info("Create task button clicked");
    }

    /**
     * Заполняет поле имени задачи.
     *
     * @param text Имя задачи.
     */
    public void fillTaskNameField(String text){
        taskNameInput.fill(text);
        logger.info("Task filled with text - " + text);
    }

    /**
     * Нажимает кнопку добавления задачи.
     */
    public void clickAddTaskButton(){
        addTaskButton.click();
        logger.info("Task added");
    }

    /**
     * Наводит указатель мыши на кнопку "Сегодня".
     */
    public void hoverTodayButton(){
        todayButton.hover();
        logger.info("Hover to today button");
    }

    /**
     * Нажимает кнопку описания задачи.
     */
    public void clickDescriptionButton(){
        descriptionButton.click();
        logger.info("Description button clicked");
    }

    /**
     * Заполняет поле описания задачи.
     *
     * @param text Описание задачи.
     */
    public void fillDescriptionField(String text){
        descriptionInput.fill(text);
        logger.info("Description filled with text - " + text);
    }
}
