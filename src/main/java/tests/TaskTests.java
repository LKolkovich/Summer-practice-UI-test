package tests;

import pages.HomePage;
import pages.popUpWindows.windowForTasks.TasksDescriptionWindow;
import pages.popUpWindows.windowForTasks.TasksWindow;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Класс для тестирования создания и удаления задач с разными функциями.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskTests extends BaseTest {

    private final String taskNameForCreatingTask = "First Task";
    private final String taskNameForCreatingTaskWithOutName = "";
    private final String errorMsgForCreatingTaskWithOutName = "Enter Task Name";
    private final String taskNameForTaskWithDescription = "New task";
    private final String taskDescriptionForTestWithDescription = "First Description";
    private final String taskNameForTaskWithOutDescription = "New task1";
    private final String taskDescriptionForTestWithOutDescription = "";
    private final String buttonNameForTestWithOutDescription = "Add description";
    private final String taskNameForPriority = "Task1";
    private final String priorityName = "Urgent";
    private final String taskNameForEmptyPriority = "Task2";
    private final String priorityNnameForTestWithEmptyPriority = "";
    private final String buttonNameForTestWithEmptyPriority = "Empty";
    private final String taskNameForDeletingTask = "new1";
    private final String taskNameForDoneTask = "new2";

    /**
     * Создает задачу с указанным именем.
     *
     * @param homePage Объект главной страницы.
     * @param taskName Имя создаваемой задачи.
     * @return Объект окна задач.
     */
    private TasksWindow creatingTask(HomePage homePage, String taskName){
        TasksWindow tasksWindow = homePage.clickTaskButton();
        tasksWindow.hoverTodayButton();
        tasksWindow.clickAddTaskButton();
        tasksWindow.fillTaskNameField(taskName);
        tasksWindow.clickCreateTaskButton();
        return tasksWindow;
    }

    /**
     * Создает задачу с описанием.
     *
     * @param homePage Объект главной страницы.
     * @param taskName Имя создаваемой задачи.
     * @param taskDescription Описание задачи.
     * @return Объект окна задач.
     */
    private TasksWindow createAndFillDescription(HomePage homePage, String taskName, String taskDescription){
        TasksWindow tasksWindow = homePage.clickTaskButton();
        tasksWindow.hoverTodayButton();
        tasksWindow.clickAddTaskButton();
        tasksWindow.fillTaskNameField(taskName);
        tasksWindow.clickDescriptionButton();
        tasksWindow.fillDescriptionField(taskDescription);
        tasksWindow.clickCreateTaskButton();
        return tasksWindow;
    }

    /**
     * Тест создания задачи с именем.
     */
    @Test
    @Order(1)
    public void testCreatingTaskWithName(){
        HomePage homePage = auth();
        TasksWindow tasksWindow = creatingTask(homePage, taskNameForCreatingTask);
        assertTrue(tasksWindow.isTaskPresent(taskNameForCreatingTask, true),
                "Task " + taskNameForCreatingTask + " was not found!");
    }

    /**
     * Тест создания задачи без имени.
     */
    @Test
    @Order(2)
    public void testCreatingTaskWithoutName(){
        HomePage homePage = auth();
        TasksWindow tasksWindow = creatingTask(homePage, taskNameForCreatingTaskWithOutName);
        assertTrue(tasksWindow.isTaskPresent(errorMsgForCreatingTaskWithOutName, false),
                "Messg " + errorMsgForCreatingTaskWithOutName + " was not found!");
    }

    /**
     * Тест создания задачи с описанием.
     */
    @Test
    public void testCreatingTaskWithDescription(){
        HomePage homePage = auth();
        TasksWindow tasksWindow = createAndFillDescription(
                homePage, taskNameForTaskWithDescription, taskDescriptionForTestWithDescription
        );
        TasksDescriptionWindow tasksDescriptionWindow = tasksWindow.openTask(taskNameForTaskWithDescription);
        assertTrue(tasksDescriptionWindow.isDescriptionPresent(
                taskDescriptionForTestWithDescription, true
        ), "Description " + taskDescriptionForTestWithDescription + " was not found!");
    }

    /**
     * Тест создания задачи без описания.
     */
    @Test
    public void testCreatingTaskWithOutDescription(){
        HomePage homePage = auth();
        TasksWindow tasksWindow = createAndFillDescription(
                homePage, taskNameForTaskWithOutDescription, taskDescriptionForTestWithOutDescription
        );
        TasksDescriptionWindow tasksDescriptionWindow = tasksWindow.openTask(taskNameForTaskWithOutDescription);
        assertTrue(tasksDescriptionWindow.isDescriptionPresent(
                buttonNameForTestWithOutDescription, false
        ), "Messg " + buttonNameForTestWithOutDescription + " was not found!");
    }

    /**
     * Тест создания задачи с приоритетом.
     */
    @Test
    public void testCreatingTaskWithPriority(){
        HomePage homePage = auth();
        TasksWindow tasksWindow = creatingTask(homePage, taskNameForPriority);
        TasksDescriptionWindow tasksDescriptionWindow = tasksWindow.openTask(taskNameForPriority);
        tasksDescriptionWindow.clickPriorityButton();
        tasksDescriptionWindow.setPriority(priorityName);
        assertTrue(tasksDescriptionWindow.isPrioritySet(priorityName, true),
                "Priority " + priorityName + " was not found");
    }

    /**
     * Тест создания задачи без приоритета.
     */
    @Test
    public void testCreatingTaskWithOutPriority(){
        HomePage homePage = auth();
        TasksWindow tasksWindow = creatingTask(homePage, taskNameForEmptyPriority);
        TasksDescriptionWindow tasksDescriptionWindow = tasksWindow.openTask(taskNameForEmptyPriority);
        assertTrue(tasksDescriptionWindow.isPrioritySet(priorityNnameForTestWithEmptyPriority, false),
                "Msg " + buttonNameForTestWithEmptyPriority + " was not found");
    }

    /**
     * Тест удаления задачи.
     */
    @Test
    public void testDeletingTask(){
        HomePage homePage = auth();
        TasksWindow tasksWindow = creatingTask(homePage, taskNameForDeletingTask);
        TasksDescriptionWindow tasksDescriptionWindow = tasksWindow.openTask(taskNameForDeletingTask);
        tasksDescriptionWindow.openTaskSettings();
        tasksDescriptionWindow.clickDeleteButton();
        homePage.clickTaskButton();
        assertFalse(tasksWindow.isTaskPresent(taskNameForDeletingTask, true),
                "Task " + taskNameForDeletingTask + " was not deleted");
    }

    /**
     * Тест омтетки задачи как выполненной.
     */
    @Test
    public void testDoneTask(){
        HomePage homePage = auth();
        TasksWindow tasksWindow = creatingTask(homePage, taskNameForDoneTask);
        TasksDescriptionWindow tasksDescriptionWindow = tasksWindow.openTask(taskNameForDoneTask);
        tasksDescriptionWindow.clickDoneButton();
        tasksDescriptionWindow.closeWindow();
        homePage.clickTaskButton();
        tasksWindow.clickSwitchTasks();
        assertTrue(tasksWindow.isMarkedDone(taskNameForDoneTask, true),
                "Task " + taskNameForDoneTask + " was not marked done");
    }
}
